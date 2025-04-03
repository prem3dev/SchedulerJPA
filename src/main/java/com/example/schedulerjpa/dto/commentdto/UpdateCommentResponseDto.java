package com.example.schedulerjpa.dto.commentdto;

import com.example.schedulerjpa.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCommentResponseDto {

    private final Long id;

    private final Long scheduleId;

    private final Long userId;

    private final String contents;

   private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UpdateCommentResponseDto (Comment comment) {
        this.id = comment.getId();
        this.scheduleId = comment.getSchedule().getId();
        this.userId = comment.getUser().getId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
