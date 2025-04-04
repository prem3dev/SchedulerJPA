package com.example.schedulerjpa.dto.userdto;

import com.example.schedulerjpa.customannotation.NotAllNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@NotAllNull
@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

    private final String userName;

    private final String email;

    @NotBlank
    private final String presentPassword;

    private final String newPassword;
}
