package com.example.schedulerjpa.dto.commentdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {

    @NotBlank
    @Size(max = 30)
    String contents;
}
