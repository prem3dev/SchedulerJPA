package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.QSchedule;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ScheduleCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // querydsl을 이용하여 동적으로 schedules 테이블, 특정 id의 각 컬럼 값을 업데이트 하려 하였으나,
    // JpaAuditing이 반영되지 않아서 참고로만 남겨두었습니다.
//    @Override
//    @Transactional
//    public long updateById(Long id, String authorName, String title, String task) {
//
//        QSchedule qSchedule = QSchedule.schedule;
//
//        JPAUpdateClause update = jpaQueryFactory.update(qSchedule);
//
//        if ((authorName != null) && !authorName.isBlank()) {
//            update.set(qSchedule.authorName, authorName);
//        }
//
//        if ((title != null) && !title.isBlank()) {
//            update.set(qSchedule.title, title);
//        }
//
//        if ((task != null) && !task.isBlank()) {
//            update.set(qSchedule.task, task);
//        }
//
//        update.where(QSchedule.schedule.id.eq(id));
//
//        return update.execute();
//    }
}