package com.example.schedulerjpa.dto.commentdto;

import com.example.schedulerjpa.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchCommentsResponseDto {

    private final Long id;

    private final String userName;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public SearchCommentsResponseDto (Comment comment) {
        this.id = comment.getId();
        this.userName = comment.getUser().getUserName();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
