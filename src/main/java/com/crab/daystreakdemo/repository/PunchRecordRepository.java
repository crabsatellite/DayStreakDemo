package com.crab.daystreakdemo.repository;

import com.crab.daystreakdemo.model.PunchRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PunchRecordRepository extends JpaRepository<PunchRecord, Long> {
}
