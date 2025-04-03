package com.example.schedulerjpa.customannotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

// Update 요청 때 동적으로 설계 하던 중 valid 단계에서 requestDto의 모든 필드가 null인지 여부의 유효성 검사가 필요하다고 생각했습니다.
// 이에 Spring의 ConstraintValidator를 사용하여 DTO의 모든 필드가 null인지 검사하는 커스텀 검증 로직을 작성하는 법을 GPT에 요청했습니다.
@Documented
@Constraint(validatedBy = NotAllNullValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAllNull {
    String message() default "모든 필드를 null로 설정할 수 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
