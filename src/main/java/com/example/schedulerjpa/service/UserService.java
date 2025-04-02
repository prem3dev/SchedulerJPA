package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.SignUpUserResponseDto;

public interface UserService {

    SignUpUserResponseDto signUpUser(String userName, String email);
}
