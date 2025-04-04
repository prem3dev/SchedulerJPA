package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.Comment;
import com.example.schedulerjpa.global.exception.CustomException;
import com.example.schedulerjpa.global.exception.Exceptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllBySchedule_Id(Long scheduleId);

    default List<Comment> findCommentsByScheduleId (Long scheduleId) {
        return findAllBySchedule_Id(scheduleId);
    }

    default Comment findCommentByCommentIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(Exceptions.COMMENT_NOT_FOUND));
    }
}
