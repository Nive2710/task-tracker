package com.nivethitha.backend.controller;

import com.nivethitha.backend.dto.TaskRequestDto;
import com.nivethitha.backend.dto.TaskResponseDto;
import com.nivethitha.backend.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.nivethitha.backend.entity.Priority;
import com.nivethitha.backend.entity.TaskStatus;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponseDto createTask(
            @Valid @RequestBody TaskRequestDto request) {

        return taskService.createTask(request);
    }

    @GetMapping
public Page<TaskResponseDto> getAllTasks(

        @RequestParam(defaultValue = "0") int page,

        @RequestParam(defaultValue = "5") int size,

        @RequestParam(required = false) TaskStatus status,

        @RequestParam(required = false) Priority priority,

        @RequestParam(defaultValue = "id") String sortBy,

        @RequestParam(defaultValue = "asc") String direction) {

    return taskService.getAllTasks(
            page,
            size,
            status,
            priority,
            sortBy,
            direction);
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