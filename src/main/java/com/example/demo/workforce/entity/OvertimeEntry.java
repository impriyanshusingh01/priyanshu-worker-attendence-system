package com.example.demo.workforce.entity;

import com.example.demo.workforce.enums.SettlementStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "overtime_entries",
        indexes = {
                @Index(name = "idx_overtime_worker", columnList = "worker_id"),
                @Index(name = "idx_overtime_date", columnList = "overtimeDate")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OvertimeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id", nullable = false, unique = true)
    private AttendanceLog attendanceLog;

    @Column(nullable = false)
    private LocalDate overtimeDate;

    @Column(nullable = false)
    private Double overtimeHours;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal overtimeRateApplied;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SettlementStatus settlementStatus = SettlementStatus.PENDING;
}
