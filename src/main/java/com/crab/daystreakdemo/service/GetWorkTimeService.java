package com.crab.daystreakdemo.service;

import com.crab.daystreakdemo.model.PunchRecord;
import com.crab.daystreakdemo.repository.PunchRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GetWorkTimeService {

    @Autowired
    private PunchRecordRepository repository;

    public Duration getWorkTime(Long uid) {
        List<PunchRecord> records = repository.findByUser_UidOrderByPunchTimeAsc(uid);

        Duration workTime = Duration.ZERO;
        LocalDateTime start = null;
        boolean onBreak = false;

        for (PunchRecord record : records) {
            switch (record.getType()) {
                case CHECK_IN -> start = record.getPunchTime();
                case CHECK_OUT -> {
                    if (start != null) {
                        workTime = workTime.plus(Duration.between(start, record.getPunchTime()));
                        start = null;
                    }
                }
                case BREAK_START -> {
                    if (start != null) {
                        workTime = workTime.plus(Duration.between(start, record.getPunchTime()));
                        start = null;
                        onBreak = true;
                    }
                }
                case BREAK_END -> {
                    if (onBreak) {
                        start = record.getPunchTime();
                        onBreak = false;
                    }
                }
            }
        }

        return workTime;
    }
}
