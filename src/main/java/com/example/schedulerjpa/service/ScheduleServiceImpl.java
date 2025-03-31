package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.CreationResponseDto;
import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public CreationResponseDto createSchedule(String authorName, String title, String task) {

        Schedule schedule = new Schedule(authorName, title, task);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreationResponseDto(
                savedSchedule.getId(),
                savedSchedule.getAuthorName(),
                savedSchedule.getTitle(),
                savedSchedule.getTask(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt());
    }
}
