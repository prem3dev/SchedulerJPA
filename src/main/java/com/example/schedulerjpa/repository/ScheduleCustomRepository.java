package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleCustomRepository {

    // querydsl을 이용하여 동적으로 schedules 테이블, 특정 id의 각 컬럼 값을 업데이트 하려 하였으나,
    // JpaAuditing이 반영되지 않아서 참고로만 남겨두었습니다.
    // public long updateById (Long id, String authorName, String title, String task);
}
