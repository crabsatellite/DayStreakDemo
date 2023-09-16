package com.crab.daystreakdemo.controller;

import com.crab.daystreakdemo.dto.GetWorkTimeRequest;
import com.crab.daystreakdemo.service.GetWorkTimeService;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/time")
public class TimeController {
    private final GetWorkTimeService service;

    public TimeController(GetWorkTimeService service) {
        this.service = service;
    }

    @GetMapping("/worktime")
    public Duration getWorkTime(
            @RequestBody
            GetWorkTimeRequest request) {
        if (request == null || request.getUid() == null) {
            throw new IllegalArgumentException("uid is required");
        }
        return service.getWorkTime(request.getUid());
    }
}