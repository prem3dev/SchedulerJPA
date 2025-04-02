package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.SignUpUserResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public SignUpUserResponseDto signUpUser(String userName, String email) {

        User user = new User(userName, email);

        User savedUser = userRepository.save(user);

        return new SignUpUserResponseDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt());
    }
}
