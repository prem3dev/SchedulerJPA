package com.example.schedulerjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpUserRequestDto {

    private final String userName;

    private final String email;
}
