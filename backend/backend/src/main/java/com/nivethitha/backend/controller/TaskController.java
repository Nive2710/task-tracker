package com.nivethitha.backend.controller;

import com.nivethitha.backend.dto.TaskRequestDto;
import com.nivethitha.backend.dto.TaskResponseDto;
import com.nivethitha.backend.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponseDto createTask(
            @Valid @RequestBody TaskRequestDto request) {

        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {

        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {

        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDto request) {

        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);
    }
}