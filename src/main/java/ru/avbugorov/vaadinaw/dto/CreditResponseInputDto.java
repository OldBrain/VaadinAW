package ru.avbugorov.vaadinaw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avbugorov.vaadinaw.constants.CreditResponseStatus;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditResponseInputDto {
    private Long requestId;
    private BigDecimal sum;
    private Integer period;
    private Float percent;
    private CreditResponseStatus status;
}
