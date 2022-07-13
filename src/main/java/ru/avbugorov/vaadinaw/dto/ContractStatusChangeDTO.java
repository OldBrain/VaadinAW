package ru.avbugorov.vaadinaw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avbugorov.vaadinaw.constants.ContractStatus;

@Data
@NoArgsConstructor
public class ContractStatusChangeDTO {
    Long contractId;
    ContractStatus status;
}
