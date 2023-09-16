package com.crab.daystreakdemo.controller;


import com.crab.daystreakdemo.model.PunchRecord;
import com.crab.daystreakdemo.service.PunchRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/punch")
public class PunchRecordController {

    @Autowired
    private PunchRecordService service;

    @PostMapping("/in")
    public PunchRecord checkIn() {
        return service.checkIn();
    }

    @PostMapping("/out")
    public PunchRecord checkOut() {
        return service.checkOut();
    }

    @PostMapping("break/start")
    public PunchRecord breakStart() {
        return service.breakStart();
    }

    @PostMapping("break/end")
    public PunchRecord breakEnd() {
        return service.breakEnd();
    }

    @GetMapping
    public List<PunchRecord> getAll() {
        return service.getAll();
    }
}
