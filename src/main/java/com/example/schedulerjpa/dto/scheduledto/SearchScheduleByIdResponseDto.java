package com.example.schedulerjpa.dto.scheduledto;

import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class SearchScheduleByIdResponseDto {

    private final Long id;

    private final String userName;

    private final String title;

    private final String task;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public SearchScheduleByIdResponseDto (Schedule schedule) {
        this.id = schedule.getId();
        this.userName = schedule.getUser().getUserName();
        this.title = schedule.getTitle();
        this.task = schedule.getTask();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
