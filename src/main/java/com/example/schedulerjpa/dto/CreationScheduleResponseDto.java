package com.example.schedulerjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreationScheduleResponseDto {

    private final Long id;

    private final String authorName;

    private final String title;

    private final String task;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;
}
