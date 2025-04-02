package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.SearchUserResponseDto;
import com.example.schedulerjpa.dto.SignUpUserResponseDto;
import com.example.schedulerjpa.dto.UpdateUserRequestDto;
import com.example.schedulerjpa.dto.UpdateUserResponseDto;
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
    public void deleteUserById(Long id) {
        User user = userRepository.findUserByIdOrElseThrow(id);

        userRepository.delete(user);
    }
}
