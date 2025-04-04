package com.example.schedulerjpa.service;

import com.example.schedulerjpa.config.PasswordEncoder;
import com.example.schedulerjpa.dto.userdto.*;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.global.exception.CustomException;
import com.example.schedulerjpa.global.exception.Exceptions;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public SignUpUserResponseDto signUpUser(SignUpUserRequestDto signUpUserRequestDto) {

        // 이메일 중복 체크 - Service 계층 CustomException 처리 예시
        if (userRepository.existsByEmail(signUpUserRequestDto.getEmail())) {
            throw new CustomException(Exceptions.EMAIL_DUPLICATION);
        }

        PasswordEncoder passwordEncoder = new PasswordEncoder();

        String encodedPassword = passwordEncoder.encode(signUpUserRequestDto.getPassword());
        User user = new User(
                signUpUserRequestDto.getUserName(),
                signUpUserRequestDto.getEmail(),
                encodedPassword);

        User savedUser = userRepository.save(user);

        return new SignUpUserResponseDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt());
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        User user = userRepository.findUserByUserEmailOrElseThrow(loginRequestDto.getEmail());

     if(passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
         return new LoginResponseDto(user.getId(),
                 user.getUserName(),
                 "안녕하세요! " + user.getUserName() + "님 환영합니다.");
     } else {throw new CustomException(Exceptions.INVALID_PASSWORD);
     }
    }

    @Override
    public List<SearchUserResponseDto> findAllUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()) {
            throw new CustomException(Exceptions.USER_NOT_FOUND);
        }
        return userList.stream().map(SearchUserResponseDto::new).toList();
    }

    @Override
    public SearchUserResponseDto findUserById(Long id) {
        User user = userRepository.findUserByIdOrElseThrow(id);
        return new SearchUserResponseDto(user);
    }

    @Transactional
    @Override
    public UpdateUserResponseDto updateUser(Long id, UpdateUserRequestDto requestDto) {

        User user = userRepository.findUserByIdOrElseThrow(id);

        PasswordEncoder passwordEncoder = new PasswordEncoder();

        if (!passwordEncoder.matches(requestDto.getPresentPassword(), user.getPassword())) {
            throw new CustomException(Exceptions.INVALID_PASSWORD);
        }
        if (requestDto.getNewPassword() != null && !requestDto.getNewPassword().isBlank()) {
            user.setPassword(requestDto.getNewPassword());
        }
        if (requestDto.getUserName() != null && !requestDto.getUserName().isBlank()) {
            user.setUserName(requestDto.getUserName());
        }
        if (requestDto.getEmail() != null && !requestDto.getEmail().isBlank()) {
            user.setEmail(requestDto.getEmail());
        }
        return new UpdateUserResponseDto(user);
    }

    @Override
    public void deleteUserById(Long id, String password) {
        User user = userRepository.findUserByIdOrElseThrow(id);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        if(passwordEncoder.matches(password, user.getPassword())) {
            userRepository.delete(user);
        } else {
          throw new CustomException(Exceptions.INVALID_PASSWORD);
        }
    }
}