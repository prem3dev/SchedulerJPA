package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreationScheduleRequestDto {

    @NotBlank
    private final String email;

    @NotBlank
    private final String title;

    private final String task;

}
