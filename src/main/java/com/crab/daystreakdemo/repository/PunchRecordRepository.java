package com.crab.daystreakdemo.repository;

import com.crab.daystreakdemo.model.PunchRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PunchRecordRepository extends JpaRepository<PunchRecord, Long> {
    // find the newest punch record
    PunchRecord findTopByOrderByPunchTimeDesc();

    // find the newest punch record by uid
    Optional<PunchRecord> findTopByUser_UidOrderByPunchTimeDesc(Long uid);

    // find all punch records by uid
    List<PunchRecord> findByUser_Uid(Long uid);
}
