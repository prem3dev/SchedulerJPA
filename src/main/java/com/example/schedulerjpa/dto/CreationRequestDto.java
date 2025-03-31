package com.example.schedulerjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreationRequestDto {

    private final String authorName;

    private final String title;

    private final String task;
}
