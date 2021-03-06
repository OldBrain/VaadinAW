package ru.avbugorov.vaadinaw.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.avbugorov.vaadinaw.constants.RequestStatus;
import ru.avbugorov.vaadinaw.data.entity.Client;
import ru.avbugorov.vaadinaw.data.entity.CreditRequest;
import ru.avbugorov.vaadinaw.data.entity.CreditResponse;
import ru.avbugorov.vaadinaw.dto.CreditRequestDto;
import ru.avbugorov.vaadinaw.dto.CreditRequestInputDto;
import ru.avbugorov.vaadinaw.exception.AwpException;
import ru.avbugorov.vaadinaw.repositories.ClientRepository;
import ru.avbugorov.vaadinaw.repositories.CreditRequestRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final CreditRequestRepository requestRepository;
    private final ClientRepository clientRepository;

    public List<CreditRequestDto> findAllRequestDto(RequestStatus status, Long clientId) {
        return requestRepository.findAll().stream()
                .filter(creditRequest -> status == null || creditRequest.getStatus().equals(status))
                .filter(creditRequest -> clientId == null || creditRequest.getClient().getId().equals(clientId))
                .map(CreditRequestDto::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<CreditRequestDto> findRequestDtoById(Long id) {
        return requestRepository.findById(id).map(CreditRequestDto::valueOf);
    }

    public void save(CreditRequestInputDto requestInputDto) {
        if (clientRepository.existsById(requestInputDto.getClientId())) {
            Client client = clientRepository.getById(requestInputDto.getClientId());
            CreditRequest newRequest = new CreditRequest();
            newRequest.setClient(client);
            newRequest.setSum(requestInputDto.getSum());
            newRequest.setPeriod(requestInputDto.getPeriod());
            newRequest.setStatus(RequestStatus.WAITING);
            requestRepository.save(newRequest);
        }
    }

    @SneakyThrows
    public void update(CreditRequestDto requestDto) {
        CreditRequest request = requestRepository.findById(requestDto.getId()).orElseThrow(() ->
                new NoSuchElementException(String.format("Incorrect credit request ID: %d", requestDto.getId())));
        RequestStatus status = request.getStatus();
        if (status != RequestStatus.WAITING) {
            throw new AwpException("Request editing with status: " + status + " is forbidden");
        }
        request.setSum(requestDto.getSum());
        request.setPeriod(requestDto.getPeriod());
        request.setStatus(requestDto.getStatus());
        requestRepository.save(request);
    }

    public void updateStatusWithResponse(CreditRequest request, CreditResponse response){
        request.setCreditResponse(response);
        switch (response.getStatus()){
            case CONFIRMED:
                    request.setStatus(RequestStatus.CONFIRMED);
                    break;
            case REJECTION:
                request.setStatus(RequestStatus.REJECTION);
                break;
            default:
                request.setStatus(request.getStatus());
                break;
        }
        requestRepository.save(request);
    }

}
