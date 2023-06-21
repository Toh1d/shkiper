package com.example.springbootdemo.service;

import com.example.springbootdemo.model.Record;
import com.example.springbootdemo.model.User;
import com.example.springbootdemo.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final HttpSession session;

    @Autowired
    public AuthService(AuthRepository authRepository, HttpSession session) {
        this.authRepository = authRepository;
        this.session = session;
    }

    public HttpSession getSession() {
        return session;
    }

    public User findByLogin(String login) {
        return authRepository.findByLogin(login);
    }

    public boolean isAuthorized() {
        return session.getAttribute("user") != null;
    }

}
