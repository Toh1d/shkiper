package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Record;
import com.example.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AuthRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
