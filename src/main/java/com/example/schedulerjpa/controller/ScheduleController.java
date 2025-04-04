package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.common.Const;
import com.example.schedulerjpa.dto.scheduledto.*;
import com.example.schedulerjpa.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 생성
    @PostMapping
    public ResponseEntity<CreationScheduleResponseDto> createSchedule(
            @SessionAttribute(name = Const.LOGIN_USER) Long id,
            @RequestBody @Valid CreationScheduleRequestDto requestDto) {
        CreationScheduleResponseDto responseDto =
                scheduleService.createSchedule(id, requestDto.getTitle(), requestDto.getTask()
                );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //전체 일정 조회
    @GetMapping("/total")
    public ResponseEntity<List<SearchScheduleResponseDto>> findAllSchedules() {
        return new ResponseEntity<>(scheduleService.findAllSchedules(), HttpStatus.OK);
    }

    //개별 일정 조회
    @Validated
    @GetMapping("/each/{id}")
    public ResponseEntity<SearchScheduleResponseDto> findScheduleById (
            @PathVariable @NotNull Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    //개별 일정 동적 수정
    @Validated
    @PatchMapping("/individuals")
    public ResponseEntity<UpdateScheduleResponseDto> updateSchedule(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid UpdateScheduleRequestDto requestDto,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) Long loginUserId) {

      return new ResponseEntity<>(scheduleService.updateSchedule(id, requestDto, loginUserId), HttpStatus.OK);
    }

    //개별 일정 삭제
    @Validated
    @DeleteMapping("/individuals")
    public ResponseEntity<Void> deleteSchedule (
            @PathVariable @NotNull Long id,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) Long loginUserId) {
        scheduleService.deleteSchedule(id, loginUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}