package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.CreationRequestDto;
import com.example.schedulerjpa.dto.CreationResponseDto;
import com.example.schedulerjpa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreationResponseDto> createSchedule(@RequestBody CreationRequestDto requestDto) {

        CreationResponseDto responseDto =
                scheduleService.createSchedule(
                        requestDto.getAuthorName(),
                        requestDto.getTitle(),
                        requestDto.getTask()
                );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
