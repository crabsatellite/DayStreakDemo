package com.crab.daystreakdemo.service;

import com.crab.daystreakdemo.model.PunchRecord;
import com.crab.daystreakdemo.model.PunchType;
import com.crab.daystreakdemo.model.User;
import com.crab.daystreakdemo.repository.PunchRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PunchRecordService {
    private final PunchRecordRepository repository;
    public PunchRecordService(PunchRecordRepository repository) {
        this.repository = repository;
    }

    public PunchRecord checkIn(Long uid) {
        Optional<PunchRecord> lastRecordOptional = repository.findTopByUser_UidOrderByPunchTimeDesc(uid);
        if (lastRecordOptional.isPresent()) {
            PunchRecord lastRecord = lastRecordOptional.get();
            if (lastRecord.getType() != PunchType.CHECK_OUT) {
                throw new IllegalStateException("Cannot check in again before you check out");
            }
        }
        PunchRecord newRecord = new PunchRecord(LocalDateTime.now(), PunchType.CHECK_IN);
        User user = new User();
        user.setId(uid);
        newRecord.setUser(user);

        return repository.save(newRecord);
    }

    public PunchRecord checkOut(Long uid) {
        Optional<PunchRecord> lastRecordOptional = repository.findTopByUser_UidOrderByPunchTimeDesc(uid);
        if (lastRecordOptional.isEmpty()) {
            throw new IllegalStateException("No previous record found for user with UID: " + uid);
        }
        PunchRecord lastRecord = lastRecordOptional.get();
        if (lastRecord.getType() == PunchType.BREAK_START) {
            throw new IllegalStateException("Cannot check out before break end");
        }
        if (lastRecord.getType() == PunchType.CHECK_OUT) {
            throw new IllegalStateException("Cannot check out again");
        }
        PunchRecord newRecord = new PunchRecord(LocalDateTime.now(), PunchType.CHECK_OUT);
        User user = new User();
        user.setId(uid);
        newRecord.setUser(user);

        return repository.save(newRecord);
    }

    public PunchRecord breakStart(Long uid) {
        Optional<PunchRecord> lastRecordOptional = repository.findTopByUser_UidOrderByPunchTimeDesc(uid);
        if (lastRecordOptional.isEmpty()) {
            throw new IllegalStateException("No previous record found for user with UID: " + uid);
        }
        PunchRecord lastRecord = lastRecordOptional.get();
        if (lastRecord.getType() == PunchType.BREAK_START) {
            throw new IllegalStateException("Cannot start break again");
        }
        if (lastRecord.getType() == PunchType.CHECK_OUT) {
            throw new IllegalStateException("Cannot start break after check out");
        }
        PunchRecord newRecord = new PunchRecord(LocalDateTime.now(), PunchType.BREAK_START);
        User user = new User();
        user.setId(uid);
        newRecord.setUser(user);

        return repository.save(newRecord);
    }

    public PunchRecord breakEnd(Long uid) {
        Optional<PunchRecord> lastRecordOptional = repository.findTopByUser_UidOrderByPunchTimeDesc(uid);
        if (lastRecordOptional.isEmpty()) {
            throw new IllegalStateException("No previous record found for user with UID: " + uid);
        }
        PunchRecord lastRecord = lastRecordOptional.get();
        if (lastRecord.getType() == PunchType.BREAK_END) {
            throw new IllegalStateException("Cannot end break again");
        }
        if (lastRecord.getType() == PunchType.CHECK_OUT) {
            throw new IllegalStateException("Cannot end break after check out");
        }
        if (lastRecord.getType() == PunchType.CHECK_IN) {
            throw new IllegalStateException("Cannot end break before start a break");
        }
        PunchRecord newRecord = new PunchRecord(LocalDateTime.now(), PunchType.BREAK_END);
        User user = new User();
        user.setId(uid);
        newRecord.setUser(user);

        return repository.save(newRecord);
    }

    public List<PunchRecord> getAll() {
        return repository.findAll();
    }
}
