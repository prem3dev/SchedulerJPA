package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.CreationResponseDto;

public interface ScheduleService {
   public CreationResponseDto createSchedule(String authorName, String title, String task);
}
