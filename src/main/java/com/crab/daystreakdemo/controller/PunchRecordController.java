package com.crab.daystreakdemo.controller;

import com.crab.daystreakdemo.model.PunchRecord;
import com.crab.daystreakdemo.model.PunchType;
import com.crab.daystreakdemo.repository.PunchRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/punch")
public class PunchRecordController {

    @Autowired
    private PunchRecordRepository repository;

    @PostMapping("/in")
    public PunchRecord checkIn() {
        return repository.save(new PunchRecord(LocalDateTime.now(), PunchType.CHECK_IN));
    }

    @PostMapping("/out")
    public PunchRecord checkOut() {
        return repository.save(new PunchRecord(LocalDateTime.now(), PunchType.CHECK_OUT));
    }

    @PostMapping("break/start")
    public PunchRecord breakStart() {
        return repository.save(new PunchRecord(LocalDateTime.now(), PunchType.BREAK_START));
    }

    @PostMapping("break/end")
    public PunchRecord breakEnd() {
        return repository.save(new PunchRecord(LocalDateTime.now(), PunchType.BREAK_END));
    }

    @GetMapping
    public List<PunchRecord> getAll() {
        return repository.findAll();
    }
}
