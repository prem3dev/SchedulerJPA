package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.common.Const;
import com.example.schedulerjpa.dto.userdto.*;
import com.example.schedulerjpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    //회원 가입
    @PostMapping
    public ResponseEntity<SignUpUserResponseDto> signUpUser (@RequestBody @Valid SignUpUserRequestDto requestDto) {
        return new ResponseEntity<>(userService.signUpUser(requestDto
        ), HttpStatus.CREATED);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletRequest request) {

        LoginResponseDto responseDto = userService.login(requestDto);

     HttpSession session = request.getSession();

     session.setAttribute(Const.LOGIN_USER, responseDto.getId());

     return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //유저 전체 조회
    @GetMapping("/total")
    public ResponseEntity<List<SearchUserResponseDto>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    //유저 단건 조회
    @Validated
    @GetMapping("/each/{id}")
    public ResponseEntity<SearchUserResponseDto> findUserById(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    //유저 정보 동적 수정(수정 요청시  반드시 현재 비밀번호가 필요하다.)
    @Validated
    @PatchMapping("/individuals")
    public ResponseEntity<UpdateUserResponseDto> updateUser(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) Long id,
            @RequestBody @Valid UpdateUserRequestDto requestDto) {
        return new ResponseEntity<>(userService.updateUser(id, requestDto), HttpStatus.OK);
    }

    //회원 탈퇴
    //비밀 번호를 조건으로 회원 탈퇴
    @Validated
    @DeleteMapping("/individuals")
    public ResponseEntity<Void> deleteUserById(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) Long id,
            @RequestParam @NotBlank String password) {
       userService.deleteUserById(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}