package ru.avbugorov.vaadinaw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avbugorov.vaadinaw.data.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);

    public Boolean existsAllByUsernameEquals(String username);

    public Boolean existsAllByIdEquals(Long id);

    public Boolean existsAllByEmailEquals(String email);

}
