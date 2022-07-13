package ru.avbugorov.vaadinaw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avbugorov.vaadinaw.data.entity.CreditRequest;

public interface CreditRequestRepositoriy extends JpaRepository<CreditRequest, Long> {

}
