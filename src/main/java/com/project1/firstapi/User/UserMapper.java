package com.project1.firstapi.User;

import com.project1.firstapi.Role.RoleDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getContact_number(),
                user.getRoles().stream() .map(role -> new RoleDto(role.getId(), role.getName())) .collect(Collectors.toList())
        );
    }
}



