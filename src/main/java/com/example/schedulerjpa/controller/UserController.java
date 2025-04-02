package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.common.Const;
import com.example.schedulerjpa.dto.*;
import com.example.schedulerjpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SignUpUserResponseDto> signUpUser (@RequestBody @Valid SignUpUserRequestDto requestDto) {
        return new ResponseEntity<>(userService.signUpUser(
                requestDto.getUserName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        ), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletRequest request) {

        LoginResponseDto responseDto = userService.login(requestDto.getEmail(), requestDto.getPassword());

     HttpSession session = request.getSession();

     session.setAttribute(Const.LOGIN_USER, responseDto.getId());

     return new ResponseEntity<>("안녕하세요! " + responseDto.getLoginUserName() + "님 환영합니다.", HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<List<SearchUserResponseDto>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @Validated
    @GetMapping("/individual/{id}")
    public ResponseEntity<SearchUserResponseDto> findUserById(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @Validated
    @PatchMapping("/individual/{id}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(
            @PathVariable @NotNull Long id, @RequestBody UpdateUserRequestDto requestDto) {
        return new ResponseEntity<>(userService.updateUser(id, requestDto), HttpStatus.OK);
    }

    @Validated
    @DeleteMapping("/individual/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @NotNull Long id) {
       userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}