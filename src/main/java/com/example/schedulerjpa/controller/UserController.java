package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.*;
import com.example.schedulerjpa.service.UserService;
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

    @GetMapping
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