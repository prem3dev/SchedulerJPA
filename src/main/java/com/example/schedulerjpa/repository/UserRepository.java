package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    default User findUserByUserEmailOrElseThrow(String email) {
        return findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    default User findUserByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

   Optional<User> findUserByEmailAndPassword(String email, String password);

    default User findUserByEmailAndPasswordOrElseThrow(String email, String password) {
        return findUserByEmailAndPassword(email, password).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
