package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.common.Const;
import com.example.schedulerjpa.dto.commentdto.*;
import com.example.schedulerjpa.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    // 세션의 loginUserId와 RequestBody 요청으로 받은 scheudleId로 연관관계 설정 후 댓글 저장
    @PostMapping
    public ResponseEntity<CreationCommentResponseDto> createComment (
           @SessionAttribute(name = Const.LOGIN_USER, required = false) Long loginUserId,
           @Valid @RequestBody CreationCommentRequestDto requestDto) {

        return new ResponseEntity<>(commentService.createComment(loginUserId, requestDto), HttpStatus.CREATED);
    }

    //댓글 조회
    // scheduleId를 PathVariable로 받아 특정 schedule 관련 댓글 전체 조회
    @GetMapping("/total/{scheduleId}")
    public ResponseEntity<List<SearchCommentsResponseDto>> findCommentsByScheduleId (@PathVariable Long scheduleId) {
        return new ResponseEntity<>(commentService.findCommentsByScheduleId(scheduleId), HttpStatus.OK);
    }

    //댓글 수정
    // 로그인한 유저가 본인의 댓글만 수정 할 수 있도록 로직 구성
    @Validated
    @PatchMapping("/individual/{commentId}")
    public ResponseEntity<UpdateCommentResponseDto> updateCommentByCommentId (
            @PathVariable @NotNull Long commentId,
            @RequestParam @NotBlank @Size(max = 30) String contents,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) Long loginUserId) {
        return new ResponseEntity<>(commentService.updateCommentByCommentId(loginUserId, commentId, contents), HttpStatus.OK);
    }

    //댓글 삭제
    // 로그인한 유저가 본인의 댓글만 삭제 할 수 있도록 로직 구성
    @DeleteMapping("/individual/{commentId}")
    public ResponseEntity<Void> deleteCommentByCommentId (
            @PathVariable @NotNull Long commentId, @SessionAttribute(name = Const.LOGIN_USER, required = false) Long loginUserId) {
        commentService.deleteCommentByCommentId(commentId, loginUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
