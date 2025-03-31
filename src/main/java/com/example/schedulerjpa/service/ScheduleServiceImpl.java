package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.CreationScheduleResponseDto;
import com.example.schedulerjpa.dto.SearchScheduleResponseDto;
import com.example.schedulerjpa.dto.UpdateScheduleRequestDto;
import com.example.schedulerjpa.dto.UpdateScheduleResponseDto;
import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public CreationScheduleResponseDto createSchedule(String authorName, String title, String task) {

        Schedule schedule = new Schedule(authorName, title, task);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreationScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getAuthorName(),
                savedSchedule.getTitle(),
                savedSchedule.getTask(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt());
    }

    @Override
    public SearchScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        return new SearchScheduleResponseDto(schedule);
    }

    @Override
    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {

        scheduleRepository.findByIdOrElseThrow(id);
        return null;
    }

    @Override
    public void deleteSchedule(Long id) {

        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(schedule);
    }
}
