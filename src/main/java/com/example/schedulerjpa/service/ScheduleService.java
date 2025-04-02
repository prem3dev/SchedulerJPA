package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.CreationScheduleResponseDto;
import com.example.schedulerjpa.dto.SearchScheduleResponseDto;
import com.example.schedulerjpa.dto.UpdateScheduleRequestDto;
import com.example.schedulerjpa.dto.UpdateScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    public CreationScheduleResponseDto createSchedule(String email, String title, String task);

    public List<SearchScheduleResponseDto> findAllSchedules();

    public SearchScheduleResponseDto findScheduleById(Long id);

    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto);

   public void deleteSchedule(Long id);
}
