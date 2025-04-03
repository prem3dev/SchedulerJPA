package com.example.schedulerjpa.dto.userdto;

import com.example.schedulerjpa.customannotation.NotAllNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@NotAllNull
@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

    private final String userName;

    private final String email;

    private final String presentPassword;

    private final String newPassword;
}
