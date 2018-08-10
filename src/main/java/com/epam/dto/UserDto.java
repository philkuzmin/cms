package com.epam.dto;

import com.epam.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String login;
    private String role;
    private String state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .role(user.getRole().toString())
                .state(user.getState().toString())
                .build();
    }
}
