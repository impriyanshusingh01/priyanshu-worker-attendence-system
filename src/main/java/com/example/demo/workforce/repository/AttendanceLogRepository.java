package com.example.demo.workforce.repository;

import com.example.demo.workforce.entity.AttendanceLog;
import com.example.demo.workforce.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
    Optional<AttendanceLog> findByWorkerAndClockOutIsNull(Worker worker);
}
