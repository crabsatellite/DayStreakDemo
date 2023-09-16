package com.crab.daystreakdemo.service;

import com.crab.daystreakdemo.model.User;
import com.crab.daystreakdemo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserRepository repository;

    public RegisterService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(User user) {
        return repository.save(user);
    }
}
