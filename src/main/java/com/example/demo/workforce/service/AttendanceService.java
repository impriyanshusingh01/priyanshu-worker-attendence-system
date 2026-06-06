package com.example.demo.workforce.service;
import com.example.demo.workforce.entity.AttendanceLog;
import com.example.demo.workforce.entity.Site;
import com.example.demo.workforce.entity.Worker;
import com.example.demo.workforce.io.request.ClockInRequest;
import com.example.demo.workforce.io.request.ClockOutRequest;
import com.example.demo.workforce.repository.AttendanceLogRepository;
import com.example.demo.workforce.repository.OvertimeEntryRepository;
import com.example.demo.workforce.repository.SiteRepository;
import com.example.demo.workforce.repository.WorkerRepository;
import com.example.demo.workforce.entity.OvertimeEntry;
import com.example.demo.workforce.enums.SettlementStatus;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final WorkerRepository workerRepository;
    private final SiteRepository siteRepository;
    private final AttendanceLogRepository attendanceLogRepository;
    private final OvertimeEntryRepository overtimeEntryRepository;

    public void clockIn(ClockInRequest request) {

        Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow(() ->
                        new RuntimeException("Worker not found"));

        Site site = siteRepository.findById(request.getSiteId())
                .orElseThrow(() ->
                        new RuntimeException("Site not found"));

        if (!worker.getActive()) {
            throw new RuntimeException("Worker is inactive");
        }

        if (!site.getActive()) {
            throw new RuntimeException("Site is inactive");
        }

        attendanceLogRepository
                .findByWorkerAndClockOutIsNull(worker)
                .ifPresent(attendance -> {
                    throw new RuntimeException(
                            "Worker already clocked in");
                });

        AttendanceLog attendanceLog = new AttendanceLog();

        attendanceLog.setWorker(worker);
        attendanceLog.setSite(site);
        attendanceLog.setWorkDate(LocalDate.now());
        attendanceLog.setClockIn(LocalDateTime.now());

        attendanceLogRepository.save(attendanceLog);
    }

    public void clockOut(ClockOutRequest request) {
        Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow(() ->
                        new RuntimeException("Worker not found"));

        AttendanceLog attendanceLog =
                attendanceLogRepository
                        .findByWorkerAndClockOutIsNull(worker)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Worker is not clocked in"));

        LocalDateTime clockOutTime = LocalDateTime.now();

        attendanceLog.setClockOut(clockOutTime);

        double totalHoursWorked =
                Duration.between(
                        attendanceLog.getClockIn(),
                        clockOutTime
                ).toMinutes() / 60.0;

        attendanceLog.setTotalHoursWorked(totalHoursWorked);

        double overtimeHours =
                Math.max(0, totalHoursWorked - 8);

        attendanceLog.setOvertimeHours(overtimeHours);

        if (totalHoursWorked > 16) {
            attendanceLog.setFlagged(true);
        }

        attendanceLogRepository.save(attendanceLog);

        if (overtimeHours > 0) {

            BigDecimal amount =
                    worker.getDailyWageRate()
                            .multiply(BigDecimal.valueOf(1.5))
                            .multiply(BigDecimal.valueOf(overtimeHours));

            OvertimeEntry overtimeEntry = new OvertimeEntry();

            overtimeEntry.setWorker(worker);
            overtimeEntry.setAttendanceLog(attendanceLog);
            overtimeEntry.setOvertimeDate(LocalDate.now());
            overtimeEntry.setOvertimeHours(overtimeHours);
            overtimeEntry.setOvertimeRateApplied(
                    BigDecimal.valueOf(1.5));
            overtimeEntry.setAmount(amount);
            overtimeEntry.setSettlementStatus(
                    SettlementStatus.PENDING);

            overtimeEntryRepository.save(overtimeEntry);
        }
    }
}
