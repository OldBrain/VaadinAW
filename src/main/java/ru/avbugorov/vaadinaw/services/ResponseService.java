package ru.avbugorov.vaadinaw.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.avbugorov.vaadinaw.constants.CreditResponseStatus;
import ru.avbugorov.vaadinaw.data.entity.CreditRequest;
import ru.avbugorov.vaadinaw.data.entity.CreditResponse;
import ru.avbugorov.vaadinaw.dto.CreditResponseDto;
import ru.avbugorov.vaadinaw.dto.CreditResponseInputDto;
import ru.avbugorov.vaadinaw.repositories.CreditRequestRepository;
import ru.avbugorov.vaadinaw.repositories.CreditResponseRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final CreditResponseRepository responseRepository;
    private final CreditRequestRepository requestRepository;
    private final RequestService requestService;

    public List<CreditResponseDto> findAll(CreditResponseStatus status, Long clientId){
        return responseRepository.findAll().stream()
                .filter(creditResponse -> status == null || creditResponse.getStatus().equals(status))
                .filter((creditResponse -> clientId == null || creditResponse.getClient().getId().equals(clientId)))
                .map(CreditResponseDto::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<CreditResponseDto> findById(Long id){
        return  responseRepository.findById(id).map(CreditResponseDto::valueOf);
    }

    @SneakyThrows
    public void save(CreditResponseInputDto responseDto){
        CreditRequest request = requestRepository.findById(responseDto.getRequestId())
                .orElseThrow(() -> new NoSuchElementException("Credit request witn ID = " + responseDto.getRequestId()+ " not found"));
        if (request.getCreditResponse() != null){
            throw new IllegalArgumentException("Incorrect input: credit response already exists!");
           // new AwpException("Incorrect input: credit response already exists!");
        }
        CreditResponse response = new CreditResponse();
        switch (responseDto.getStatus()){
            case REJECTION:
                response.setPeriod(request.getPeriod());
                response.setSum(request.getSum());
                response.setStatus(responseDto.getStatus());
                response.setPercent(0f);
                response.setClient(request.getClient());
                break;
            case CONFIRMED:
                response.setSum(responseDto.getSum());
                response.setPeriod(responseDto.getPeriod());
                response.setPercent(responseDto.getPercent());
                response.setClient(request.getClient());
                response.setStatus(responseDto.getStatus());
                break;
            default:
                throw new IllegalArgumentException("Wrong operation: entered status is incorrect!");
        }
        CreditResponse newResponse = responseRepository.save(response);
        requestService.updateStatusWithResponse(request,newResponse);
    }

    @SneakyThrows
    public void update(CreditResponseDto responseDto) {
        CreditResponse response = responseRepository.findById(responseDto.getId()).orElseThrow(() ->
                new NoSuchElementException(String.format("Incorrect credit response ID: %d", responseDto.getId())));
        if (response.getContract() != null){
            throw new RuntimeException("Editing is forbidden, there is a contract");
          //  new AwpException("Редактирование запрещено, существует контракт");
        }
        response.setPeriod(responseDto.getPeriod());
        response.setSum(responseDto.getSum());
        response.setPercent(responseDto.getPercent());
        response.setStatus(responseDto.getStatus());
        CreditRequest request = requestRepository.findByCreditResponse(response).orElseThrow(() ->
                new NoSuchElementException(("The corresponding credit request not found")));
        responseRepository.save(response);
        requestService.updateStatusWithResponse(request, response);

    }
}