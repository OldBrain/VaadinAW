package ru.avbugorov.vaadinaw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avbugorov.vaadinaw.data.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
