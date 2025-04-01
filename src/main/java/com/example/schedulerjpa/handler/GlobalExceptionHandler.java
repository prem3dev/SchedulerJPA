package com.example.schedulerjpa.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 존재하지 않는 일정을 찾는 경우
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>("존재하지 않은 일정입니다. " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 런타임 중 예외가 발생한 경우
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>("accured runtime exception" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 요청 데이터 타입과 requestDto의 필드 타입이 맞지 않는 경우
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("invalid request field value" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 요청 데이터 타입과 Api 메서드 파라미터 타입이 맞지 않는 경우
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("invalid request value" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
