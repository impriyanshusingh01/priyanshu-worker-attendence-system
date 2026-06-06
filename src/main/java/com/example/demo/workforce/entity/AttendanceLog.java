package com.example.demo.workforce.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "attendance_logs",
        indexes = {
                @Index(name = "idx_attendance_worker", columnList = "worker_id"),
                @Index(name = "idx_attendance_site", columnList = "site_id"),
                @Index(name = "idx_attendance_date", columnList = "workDate")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    @Column(nullable = false)
    private LocalDate workDate;

    @Column(nullable = false)
    private LocalDateTime clockIn;

    private LocalDateTime clockOut;

    private Double totalHoursWorked;

    private Double overtimeHours;

    @Column(nullable = false)
    private Boolean flagged = false;
}
