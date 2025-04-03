package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.commentdto.CreationCommentRequestDto;
import com.example.schedulerjpa.dto.commentdto.CreationCommentResponseDto;
import com.example.schedulerjpa.dto.commentdto.SearchCommentsResponseDto;
import com.example.schedulerjpa.dto.commentdto.UpdateCommentResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public interface CommentService {
    CreationCommentResponseDto createComment(Long id, @Valid CreationCommentRequestDto requestDto);

    List<SearchCommentsResponseDto> findCommentsByScheduleId(Long id);

    UpdateCommentResponseDto updateCommentByCommentId(@NotNull Long loginUserId, Long commentId, @NotBlank @Size(max = 30) String contents);

    void deleteCommentByCommentId(@NotNull Long commentId, Long loginUserId);
}
