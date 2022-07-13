package ru.avbugorov.vaadinaw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avbugorov.vaadinaw.data.entity.CreditResponse;

import java.util.List;

@Repository
public interface CreditResponseRepository extends JpaRepository<CreditResponse, Long> {
    List<CreditResponse> findCreditResponsesByStatus(String status);
}
