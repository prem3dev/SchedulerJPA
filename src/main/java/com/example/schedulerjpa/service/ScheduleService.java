package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.scheduledto.CreationScheduleResponseDto;
import com.example.schedulerjpa.dto.scheduledto.SearchScheduleResponseDto;
import com.example.schedulerjpa.dto.scheduledto.UpdateScheduleRequestDto;
import com.example.schedulerjpa.dto.scheduledto.UpdateScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    public CreationScheduleResponseDto createSchedule(Long id, String title, String task);

    public List<SearchScheduleResponseDto> findAllSchedules();

    public SearchScheduleResponseDto findScheduleById(Long id);

    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto, Long loginUserId);

   public void deleteSchedule(Long id, Long loginUserId);
}
