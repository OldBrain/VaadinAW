package ru.avbugorov.vaadinaw.data.entity.abstractentity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;


@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class AbstractContractTemplate extends AbstractEntity {

    @Column(name = "period")
    private Integer period;

    @Column(name = "sum")
    private BigDecimal sum;

}

