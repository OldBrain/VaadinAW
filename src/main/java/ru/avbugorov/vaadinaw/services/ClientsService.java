package ru.avbugorov.vaadinaw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.avbugorov.vaadinaw.data.entity.Client;
import ru.avbugorov.vaadinaw.dto.ClientViewDto;
import ru.avbugorov.vaadinaw.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientsService {
    private final ClientRepository clientRepository;

    public ResponseEntity<?> save(Client client) {
        clientRepository.save(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<ClientViewDto> findAll() {
        return clientRepository.findAll().stream().map(ClientViewDto::valueOf)
                .collect(Collectors.toList());
    }



    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }
}
