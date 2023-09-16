package com.crab.daystreakdemo.controller;


import com.crab.daystreakdemo.dto.BreakEndRequest;
import com.crab.daystreakdemo.dto.BreakStartRequest;
import com.crab.daystreakdemo.dto.CheckInRequest;
import com.crab.daystreakdemo.dto.CheckOutRequest;
import com.crab.daystreakdemo.model.PunchRecord;
import com.crab.daystreakdemo.service.PunchRecordService;
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
    public PunchRecord checkOut(@RequestBody CheckOutRequest request) {
        if (request == null || request.getUid() == null) {
            throw new IllegalArgumentException("uid is required");
        }
        return service.checkOut(request.getUid());
    }

    @PostMapping("break/start")
    public PunchRecord breakStart(@RequestBody BreakStartRequest request) {
        if (request == null || request.getUid() == null) {
            throw new IllegalArgumentException("uid is required");
        }
        return service.breakStart(request.getUid());
    }

    @PostMapping("break/end")
    public PunchRecord breakEnd(@RequestBody BreakEndRequest request) {
        if (request == null || request.getUid() == null) {
            throw new IllegalArgumentException("uid is required");
        }
        return service.breakEnd(request.getUid());
    }

    @GetMapping
    public List<PunchRecord> getAll() {
        return service.getAll();
    }
}
