package com.project1.firstapi.User;

import com.project1.firstapi.Role.RoleDto;

import java.util.List;

public record UserDto(String name, String surname, String email, String contact_number, List<RoleDto> roles) {
}
