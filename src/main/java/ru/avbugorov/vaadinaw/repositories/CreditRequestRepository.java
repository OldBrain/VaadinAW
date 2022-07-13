package ru.avbugorov.vaadinaw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avbugorov.vaadinaw.data.entity.CreditRequest;
import ru.avbugorov.vaadinaw.data.entity.CreditResponse;

import java.util.Optional;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {

    Optional<CreditRequest> findByCreditResponse(CreditResponse response);
}
