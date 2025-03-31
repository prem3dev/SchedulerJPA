package com.example.schedulerjpa.dto;

import com.example.schedulerjpa.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateScheduleResponseDto {

    private final Long id;

    private final String authorName;

    private final String title;

    private final String task;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponseDto(Schedule schedule) {

        this.id = schedule.getId();
        this.authorName = schedule.getAuthorName();
        this.title = schedule.getTitle();
        this.task = schedule.getTask();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}