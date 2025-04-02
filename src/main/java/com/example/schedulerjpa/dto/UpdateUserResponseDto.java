package com.example.schedulerjpa.dto;

import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateUserResponseDto {


    private final Long id;

    private final String userName;

    private final String email;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UpdateUserResponseDto(User user) {

        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
