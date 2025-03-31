package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.CreationScheduleResponseDto;
import com.example.schedulerjpa.dto.SearchScheduleResponseDto;
import com.example.schedulerjpa.dto.UpdateScheduleRequestDto;
import com.example.schedulerjpa.dto.UpdateScheduleResponseDto;

public interface ScheduleService {

    public CreationScheduleResponseDto createSchedule(String authorName, String title, String task);

    public SearchScheduleResponseDto findScheduleById(Long id);

    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto);

   public void deleteSchedule(Long id);
}
