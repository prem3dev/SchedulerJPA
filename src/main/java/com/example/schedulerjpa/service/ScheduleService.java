package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.scheduledto.*;

import java.util.List;

public interface ScheduleService {

    public CreationScheduleResponseDto createSchedule(Long id, String title, String task);

    public List<SearchSchedulesPageResponseDto> findAllSchedules(int page, int size);

    public SearchScheduleByIdResponseDto findScheduleById(Long id);

    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto, Long loginUserId);

   public void deleteSchedule(Long id, Long loginUserId);
}
