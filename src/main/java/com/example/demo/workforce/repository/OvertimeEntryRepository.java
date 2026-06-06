package com.example.demo.workforce.repository;

import com.example.demo.workforce.entity.OvertimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OvertimeEntryRepository extends JpaRepository<OvertimeEntry, Long> {
}
