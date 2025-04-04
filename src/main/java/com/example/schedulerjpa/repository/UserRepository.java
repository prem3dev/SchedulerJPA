package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.global.exception.CustomException;
import com.example.schedulerjpa.global.exception.Exceptions;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    default User findUserByUserEmailOrElseThrow(String email) {
        return findUserByEmail(email).orElseThrow(() -> new CustomException(Exceptions.USER_NOT_FOUND));
    }

    default User findUserByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(Exceptions.USER_NOT_FOUND));
    }

    boolean existsByEmail(String email);
}
