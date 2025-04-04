package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.dto.scheduledto.SearchSchedulesPageResponseDto;
import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.global.exception.CustomException;
import com.example.schedulerjpa.global.exception.Exceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(Exceptions.SCHEDULE_NOT_FOUND));
    }

    @Query("SELECT new com.example.schedulerjpa.dto.scheduledto.SearchSchedulesPageResponseDto" +
            "( s.title, u.userName, s.task, s.createdAt, s.modifiedAt, COUNT(c.id)) " +
            "FROM Schedule s " +
            "LEFT JOIN s.user u " +
            "LEFT JOIN Comment c ON c.schedule = s " +
            "GROUP BY s.id, s.title, s.task, s.createdAt, s.modifiedAt, u.userName " +
            "ORDER BY s.modifiedAt DESC")
   Page<SearchSchedulesPageResponseDto> findAllSchedules(Pageable pageable);
}
