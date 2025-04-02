package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
