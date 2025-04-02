package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

    private final String userName;

    private final String email;

    private final String presentPassword;

    private final String newPassword;
}
