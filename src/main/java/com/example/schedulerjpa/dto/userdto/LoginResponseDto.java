package com.example.schedulerjpa.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private final Long id;

    private final String loginUserName;

    private final String loginMessage;
}
