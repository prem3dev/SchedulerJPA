package com.example.schedulerjpa.dto.commentdto;

import com.example.schedulerjpa.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreationCommentResponseDto {

    private final Long id;

    private final Long userId;

    private final Long scheduleId;

    private final String contents;

    private final LocalDateTime createdAt;

    public CreationCommentResponseDto (Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.userId = comment.getUser().getId();
        this.scheduleId = comment.getSchedule().getId();
        this.createdAt = comment.getCreatedAt();
    }
}
