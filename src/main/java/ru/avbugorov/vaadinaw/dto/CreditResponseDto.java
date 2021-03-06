package ru.avbugorov.vaadinaw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avbugorov.vaadinaw.constants.CreditResponseStatus;
import ru.avbugorov.vaadinaw.data.entity.CreditResponse;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditResponseDto {
    private Long id;
    private BigDecimal sum;
    private Integer period;
    private Float percent;
    private CreditResponseStatus status;
    private Long contractId;
    private Long clientId;
    private String clientName;
    private Date createdAt;
    private Date updatedAt;
    private String phone;
    private String address;
    private String pasport;

    public static CreditResponseDto valueOf(CreditResponse response) {
        CreditResponseDto responseDto = new CreditResponseDto();
        responseDto.setId(response.getId());
        responseDto.setSum(response.getSum());
        responseDto.setPeriod(response.getPeriod());
        responseDto.setPercent(response.getPercent() != null ? response.getPercent() : null);
        responseDto.setStatus(response.getStatus());
        responseDto.setContractId(response.getContract() != null ? response.getContract().getId() : null);
        responseDto.setClientId(response.getClient().getId());
        responseDto.setClientName(response.getClient().getName());
        responseDto.setCreatedAt(response.getCreatedAt());
        responseDto.setUpdatedAt(response.getUpdatedAt());
        return responseDto;
    }

}
