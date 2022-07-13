package ru.avbugorov.vaadinaw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.avbugorov.vaadinaw.constants.ContractStatus;
import ru.avbugorov.vaadinaw.data.entity.Contract;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findContractsByStatus(ContractStatus status);

    @Query(value = "select c  from Contract c where c.client.id = :client_id")
    Optional<Contract> findByClientId(@Param("client_id") Long client_id);
}
