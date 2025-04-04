package com.example.schedulerjpa.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

    private final String userName;

    private final String email;

    @NotBlank
    private final String presentPassword;

    private final String newPassword;
}
