package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.scheduledto.CreationScheduleResponseDto;
import com.example.schedulerjpa.dto.scheduledto.SearchScheduleResponseDto;
import com.example.schedulerjpa.dto.scheduledto.UpdateScheduleRequestDto;
import com.example.schedulerjpa.dto.scheduledto.UpdateScheduleResponseDto;
import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.global.exception.CustomException;
import com.example.schedulerjpa.global.exception.Exceptions;
import com.example.schedulerjpa.repository.ScheduleRepository;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public CreationScheduleResponseDto createSchedule(Long id,String title, String task) {

        Schedule schedule = new Schedule(title, task);
        User findedUser = userRepository.findUserByIdOrElseThrow(id);
        schedule.setUser(findedUser);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreationScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getTask(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt());
    }

    @Override
    public List<SearchScheduleResponseDto> findAllSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        if(scheduleList.isEmpty()) {
            throw new CustomException(Exceptions.SCHEDULE_NOT_FOUND);
        }
        return scheduleList.stream().map(SearchScheduleResponseDto::new).toList();
    }

    @Override
    public SearchScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        return new SearchScheduleResponseDto(schedule);
    }
// querydsl을 이용하여 동적으로 schedules 테이블, 특정 id의 각 컬럼 값을 업데이트 하려 하였으나,
// JpaAuditing이 반영되지 않아서 참고로만 남겨두었습니다.
//    @Override
//    @Transactional
//    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {
//
//    long row  = scheduleRepository.updateById(id, requestDto.getAuthorName(), requestDto.getTitle(), requestDto.getTask());
//
//    if(row == 0) {
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//    }
//        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
//        return new UpdateScheduleResponseDto(schedule);
//    }

    @Transactional
    @Override
    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto, Long loginUserId) {

        User user = userRepository.findUserByIdOrElseThrow(loginUserId);
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        if (!user.getPassword().equals(schedule.getUser().getPassword())) {
        throw new CustomException(Exceptions.UNAUTHORIZED_ACCESS);
        }

        if (requestDto.getTitle() != null && !requestDto.getTitle().isBlank()) {
            schedule.setTitle(requestDto.getTitle());
        }

        if (requestDto.getTask() != null && !requestDto.getTask().isBlank()) {
            schedule.setTask(requestDto.getTask());
        }
        return new UpdateScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public void deleteSchedule(Long id, Long loginUserId) {
        User user = userRepository.findUserByIdOrElseThrow(loginUserId);
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        if (!user.getPassword().equals(schedule.getUser().getPassword())) {
            throw new CustomException(Exceptions.UNAUTHORIZED_ACCESS);
        }
        scheduleRepository.delete(schedule);
    }
}