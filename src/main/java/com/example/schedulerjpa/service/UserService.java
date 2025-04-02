package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.SearchUserResponseDto;
import com.example.schedulerjpa.dto.SignUpUserResponseDto;
import com.example.schedulerjpa.dto.UpdateUserRequestDto;
import com.example.schedulerjpa.dto.UpdateUserResponseDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface UserService {

    SignUpUserResponseDto signUpUser(String userName, String email);

    List<SearchUserResponseDto> findAllUsers();

    SearchUserResponseDto findUserById(Long id);

    UpdateUserResponseDto updateUser(Long id, UpdateUserRequestDto requestDto);

    void deleteUserById(@NotNull Long id);
}