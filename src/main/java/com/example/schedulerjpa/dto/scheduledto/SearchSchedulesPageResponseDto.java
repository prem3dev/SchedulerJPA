package com.example.schedulerjpa.dto.scheduledto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SearchSchedulesPageResponseDto {

    private String title;
    private String userName;
    private String task;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long countComment;
}
