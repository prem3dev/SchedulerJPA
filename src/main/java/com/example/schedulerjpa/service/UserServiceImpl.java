package com.example.schedulerjpa.service;

import com.example.schedulerjpa.config.PasswordEncoder;
import com.example.schedulerjpa.dto.*;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public SignUpUserResponseDto signUpUser(SignUpUserRequestDto signUpUserRequestDto) {

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
         return new LoginResponseDto(user.getId(), user.getUserName());
     } else {throw new ResponseStatusException(HttpStatus.NOT_FOUND);
     }
    }

    @Override
    public List<SearchUserResponseDto> findAllUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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

        if (requestDto.getPresentPassword() != null && !requestDto.getPresentPassword().isBlank()) {
            PasswordEncoder passwordEncoder = new PasswordEncoder();
           if (passwordEncoder.matches(requestDto.getPresentPassword(), user.getPassword())) {
               if(requestDto.getNewPassword() != null && !requestDto.getNewPassword().isBlank()) {
                   user.setPassword(requestDto.getNewPassword());
               }
           } else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}
        }

        if (requestDto.getUserName() != null && !requestDto.getUserName().isBlank()) {
            user.setUserName(requestDto.getUserName());
        }
        if(requestDto.getEmail() != null && !requestDto.getEmail().isBlank()) {
            user.setEmail(requestDto.getEmail());
        }

        User newUser = userRepository.findUserByIdOrElseThrow(id);
        return new UpdateUserResponseDto(newUser);
    }

    @Override
    public void deleteUserById(Long id, String password) {
        User user = userRepository.findUserByIdOrElseThrow(id);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        if(passwordEncoder.matches(password, user.getPassword())) {
            userRepository.delete(user);
        } else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}
    }
}
