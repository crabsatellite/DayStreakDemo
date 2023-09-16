package com.crab.daystreakdemo.service;

import com.crab.daystreakdemo.model.PunchRecord;
import com.crab.daystreakdemo.model.PunchType;
import com.crab.daystreakdemo.repository.PunchRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PunchRecordService {
    private final PunchRecordRepository repository;
    public PunchRecordService(PunchRecordRepository repository) {
        this.repository = repository;
    }

    public PunchRecord checkIn() {
        PunchRecord lastRecord = repository.findTopByOrderByPunchTimeDesc();
        if (lastRecord != null && lastRecord.getType() != PunchType.CHECK_OUT) {
            throw new IllegalStateException("Cannot check in again before you check out");
        }
        return repository.save(new PunchRecord(LocalDateTime.now(), PunchType.CHECK_IN));
    }

    public PunchRecord checkOut() {
        PunchRecord lastRecord = repository.findTopByOrderByPunchTimeDesc();
        if (lastRecord == null) {
            throw new IllegalStateException("You need to check in first");
        }
        if (lastRecord.getType() == PunchType.BREAK_START) {
            throw new IllegalStateException("Cannot check out before break end");
        }
        if (lastRecord.getType() == PunchType.CHECK_OUT) {
            throw new IllegalStateException("Cannot check out again");
        }
        return repository.save(new PunchRecord(LocalDateTime.now(), PunchType.CHECK_OUT));
    }

    public PunchRecord breakStart() {
        return repository.save(new PunchRecord(LocalDateTime.now(), PunchType.BREAK_START));
    }

    public PunchRecord breakEnd() {
        return repository.save(new PunchRecord(LocalDateTime.now(), PunchType.BREAK_END));
    }

    public List<PunchRecord> getAll() {
        return repository.findAll();
    }
}
