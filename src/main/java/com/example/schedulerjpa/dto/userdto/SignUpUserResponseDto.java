package com.example.schedulerjpa.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SignUpUserResponseDto {

    private final Long id;

    private final String userName;

    private final String email;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;
}
