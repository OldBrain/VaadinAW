package ru.avbugorov.vaadinaw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avbugorov.vaadinaw.data.entity.Client;

@Data
@NoArgsConstructor
public class ClientViewDto {
    Long id;
    String name;
    String passport;
    String address;
    String phone;

    public static ClientViewDto valueOf(Client client) {
        ClientViewDto clientDto = new ClientViewDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setAddress(client.getAddress());
        clientDto.setPassport(clientDto.getPassport());
        clientDto.setPhone(client.getPhone());
        return clientDto;
    }
}
