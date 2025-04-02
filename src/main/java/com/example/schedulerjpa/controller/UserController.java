package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.SignUpUserRequestDto;
import com.example.schedulerjpa.dto.SignUpUserResponseDto;
import com.example.schedulerjpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SignUpUserResponseDto> signUpUser (@RequestBody SignUpUserRequestDto requestDto) {
        return new ResponseEntity<>(userService.signUpUser(
                requestDto.getUserName(),
                requestDto.getEmail()
        ), HttpStatus.CREATED);
    }
}
