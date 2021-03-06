package ru.avbugorov.vaadinaw.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avbugorov.vaadinaw.constants.ContractStatus;
import ru.avbugorov.vaadinaw.data.entity.abstractentity.AbstractContractTemplate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class Contract extends AbstractContractTemplate {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Column(name = "percent")
    private Float percent;

}

