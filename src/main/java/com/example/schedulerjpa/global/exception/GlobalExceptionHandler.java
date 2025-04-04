package com.example.schedulerjpa.global.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

// Response Class를 만드는 부분을 이해하고 학습할 시간이 부족해서 Enum에서 메세지를 가져와 String으로 응답만 하였습니다.
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //Custom Exception 처리 - Service 계층에서 발생한 비즈니스 예외 처리
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<String> handleCustomException(CustomException ex) {
        log.error("CustomException: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.getExceptions().getStatus()));
    }

    //Repository(JPA) 계층 예외 처리 - EntityNotFoundException 처리
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("EntityNotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(Exceptions.ENTITY_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Repository(JPA) 계층 예외 처리 - DataAccessException 처리
    //(SQL 예외, Lock 획득 실패 등 DB 관련 예외)
    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        log.error("DataAccessException: {}", e.getMessage());
        return new ResponseEntity<>("데이터베이스 접근 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 요청 데이터 타입과 requestDto의 필드 타입이 맞지 않는 경우
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException: {}", ex.getMessage());
        return new ResponseEntity<>(Exceptions.INVALID_TYPE_VALUE.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    //지원하지 않는 HTTP 메소드 호출 시 발생하는 예외 처리
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage());
        return new ResponseEntity<>(Exceptions.METHOD_NOT_ALLOWED.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    // 요청 데이터 타입과 Api 메서드 파라미터 타입이 맞지 않는 경우
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(Exceptions.INVALID_TYPE_VALUE.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // @Valid의 유용성 검증에 해당되어 예외가 발생한 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // @Validated의 유용성 검증에 해당되어 예외가 발생한 경우
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>("@Validated failed" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //필수 요청 파라미터 누락 예외 처리
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException: {}", e.getMessage());
        return new ResponseEntity<>(Exceptions.INVALID_INPUT_VALUE.getMessage(), HttpStatus.BAD_REQUEST);
    }
}