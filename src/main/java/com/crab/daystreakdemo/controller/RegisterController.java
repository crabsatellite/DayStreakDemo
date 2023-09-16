package com.crab.daystreakdemo.controller;

import com.crab.daystreakdemo.model.User;
import com.crab.daystreakdemo.service.PunchRecordService;
import com.crab.daystreakdemo.service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private final RegisterService service;
    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

}