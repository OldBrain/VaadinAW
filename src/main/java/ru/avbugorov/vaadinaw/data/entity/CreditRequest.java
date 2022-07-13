package ru.avbugorov.vaadinaw.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avbugorov.vaadinaw.constants.RequestStatus;
import ru.avbugorov.vaadinaw.data.entity.abstractentity.AbstractContractTemplate;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
@Table(name = "credit_request")
public class CreditRequest extends AbstractContractTemplate {

    //    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST },fetch = FetchType.LAZY)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "response_id")
    private CreditResponse creditResponse;
}