package com.crab.daystreakdemo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PunchRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime punchTime;
    private PunchType punchType;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    public PunchRecord(LocalDateTime punchTime, PunchType punchType) {
        this.punchTime = punchTime;
        this.punchType = punchType;
    }

    public PunchRecord() {

    }

    public PunchType getType() {
        return punchType;
    }
}

