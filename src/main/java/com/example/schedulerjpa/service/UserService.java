package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface UserService {

    SignUpUserResponseDto signUpUser(SignUpUserRequestDto signUpUserRequestDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    List<SearchUserResponseDto> findAllUsers();

    SearchUserResponseDto findUserById(Long id);

    UpdateUserResponseDto updateUser(Long id, UpdateUserRequestDto requestDto);

    void deleteUserById(@NotNull Long id, String password);
}