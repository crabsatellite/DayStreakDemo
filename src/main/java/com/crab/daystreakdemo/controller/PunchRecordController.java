package com.crab.daystreakdemo.controller;


import com.crab.daystreakdemo.dto.CheckInRequest;
import com.crab.daystreakdemo.model.PunchRecord;
import com.crab.daystreakdemo.service.PunchRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/punch")
public class PunchRecordController {
    private final PunchRecordService service;
    public PunchRecordController(PunchRecordService service) {
        this.service = service;
    }

    @PostMapping("/in")
    public PunchRecord checkIn(@RequestBody CheckInRequest request) {
        if (request == null || request.getUid() == null) {
            throw new IllegalArgumentException("uid is required");
        }
        return service.checkIn(request.getUid());
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
