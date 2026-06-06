package com.example.demo.workforce.controller;
import com.example.demo.workforce.entity.Worker;
import com.example.demo.workforce.io.request.WorkerRequest;
import com.example.demo.workforce.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workers")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;

    @PostMapping
    public Worker createWorker(
            @RequestBody WorkerRequest request) {

        return workerService.createWorker(request);
    }

    @GetMapping("/{id}")
    public Worker getWorker(
            @PathVariable Long id) {

        return workerService.getWorker(id);
    }
}
