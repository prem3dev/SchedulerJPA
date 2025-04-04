package com.example.schedulerjpa.service;
import com.example.schedulerjpa.dto.commentdto.CreationCommentRequestDto;
import com.example.schedulerjpa.dto.commentdto.CreationCommentResponseDto;
import com.example.schedulerjpa.dto.commentdto.SearchCommentsResponseDto;
import com.example.schedulerjpa.dto.commentdto.UpdateCommentResponseDto;
import com.example.schedulerjpa.entity.Comment;
import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.global.exception.CustomException;
import com.example.schedulerjpa.global.exception.Exceptions;
import com.example.schedulerjpa.repository.CommentRepository;
import com.example.schedulerjpa.repository.ScheduleRepository;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    @Override
    public CreationCommentResponseDto createComment(Long id, CreationCommentRequestDto requestDto) {
        User user = userRepository.findUserByIdOrElseThrow(id);
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(requestDto.getScheduleId());

        Comment Comment = new Comment(requestDto.getContents(), user, schedule);

        Comment savedComment = commentRepository.save(Comment);

        return new CreationCommentResponseDto(savedComment);
    }

    @Override
    public List<SearchCommentsResponseDto> findCommentsByScheduleId(Long id) {

        List<Comment> comments = commentRepository.findCommentsByScheduleId(id);

        List<SearchCommentsResponseDto> searchCommentsResponseDtos = new ArrayList<>();
        if (!comments.isEmpty()) {
            searchCommentsResponseDtos = comments.stream().map(SearchCommentsResponseDto::new).toList();
        } else {
            throw new CustomException(Exceptions.COMMENT_NOT_FOUND);
        }

        return searchCommentsResponseDtos;
    }

    @Transactional
    @Override
    public UpdateCommentResponseDto updateCommentByCommentId(Long loginUserId, Long commentId, String contents) {
        Comment comment = commentRepository.findCommentByCommentIdOrElseThrow(commentId);
        User user = userRepository.findUserByIdOrElseThrow(loginUserId);

        if (user.getPassword().equals(comment.getUser().getPassword())) {
            comment.setContents(contents);
        } else {
            throw new CustomException(Exceptions.UNAUTHORIZED_ACCESS);
        }
        return new UpdateCommentResponseDto(comment);
    }

    @Transactional
    @Override
    public void deleteCommentByCommentId(Long commentId, Long loginUserId) {
        Comment comment = commentRepository.findCommentByCommentIdOrElseThrow(commentId);
        User user = userRepository.findUserByIdOrElseThrow(loginUserId);

        if (user.getPassword().equals(comment.getUser().getPassword())) {
            commentRepository.delete(comment);
        } else {
            throw new CustomException(Exceptions.UNAUTHORIZED_ACCESS);
        }
    }
}