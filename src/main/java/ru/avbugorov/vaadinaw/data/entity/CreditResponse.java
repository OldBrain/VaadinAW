package ru.avbugorov.vaadinaw.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avbugorov.vaadinaw.constants.CreditResponseStatus;
import ru.avbugorov.vaadinaw.data.entity.abstractentity.AbstractContractTemplate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "credit_response")
public class CreditResponse extends AbstractContractTemplate {

    @Column(name = "percent")
    private Float percent;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CreditResponseStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    Contract contract;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

}
