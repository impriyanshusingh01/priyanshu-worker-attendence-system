package com.example.demo.workforce.service;
import com.example.demo.workforce.entity.Worker;
import com.example.demo.workforce.io.request.WorkerRequest;
import com.example.demo.workforce.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    public Worker createWorker(WorkerRequest request) {

        workerRepository.findByPhone(request.getPhone())
                .ifPresent(worker -> {
                    throw new RuntimeException("Phone already exists");
                });

        Worker worker = new Worker();

        worker.setName(request.getName());
        worker.setPhone(request.getPhone());
        worker.setDesignation(request.getDesignation());
        worker.setDailyWageRate(request.getDailyWageRate());
        worker.setActive(request.getActive());

        return workerRepository.save(worker);
    }

    public Worker getWorker(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Worker not found"));
    }
}
