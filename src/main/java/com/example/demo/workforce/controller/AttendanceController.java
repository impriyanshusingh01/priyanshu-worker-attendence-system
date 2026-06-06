package com.example.demo.workforce.controller;

import com.example.demo.workforce.io.request.ClockInRequest;
import com.example.demo.workforce.io.request.ClockOutRequest;
import com.example.demo.workforce.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/clock-in")
    public ResponseEntity<String> clockIn(
            @RequestBody ClockInRequest request) {

        attendanceService.clockIn(request);

        return ResponseEntity.ok("Worker clocked in successfully");
    }

    @PostMapping("/clock-out")
    public ResponseEntity<String> clockOut(
            @RequestBody ClockOutRequest request) {

        attendanceService.clockOut(request);

        return ResponseEntity.ok(
                "Worker clocked out successfully");
    }
}
