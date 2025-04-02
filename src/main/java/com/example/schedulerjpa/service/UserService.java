package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface UserService {

    SignUpUserResponseDto signUpUser(String userName, String email, String password);

    LoginResponseDto login(@NotBlank String email, @NotBlank String password);

    List<SearchUserResponseDto> findAllUsers();

    SearchUserResponseDto findUserById(Long id);

    UpdateUserResponseDto updateUser(Long id, UpdateUserRequestDto requestDto);

    void deleteUserById(@NotNull Long id);
}