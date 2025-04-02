package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpUserRequestDto {

    @NotBlank
    private final String userName;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;
}
