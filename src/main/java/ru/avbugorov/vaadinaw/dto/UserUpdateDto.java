package ru.avbugorov.vaadinaw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avbugorov.vaadinaw.constants.UserRole;
import ru.avbugorov.vaadinaw.data.entity.User;

@Data
@NoArgsConstructor
public class UserUpdateDto {
    Long id;
    String userName;
    String email;
    UserRole role;
    String password;

    public User valueOf(UserUpdateDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.userName);
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }
}
