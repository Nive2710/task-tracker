package com.nivethitha.backend.service;

import com.nivethitha.backend.dto.TaskRequestDto;
import com.nivethitha.backend.dto.TaskResponseDto;
import com.nivethitha.backend.entity.Priority;
import com.nivethitha.backend.entity.TaskStatus;
import org.springframework.data.domain.Page;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto request);

    Page<TaskResponseDto> getAllTasks(
        int page,
        int size,
        TaskStatus status,
        Priority priority,
        String sortBy,
        String direction);

    TaskResponseDto getTaskById(Long id);

    TaskResponseDto updateTask(Long id, TaskRequestDto request);

    void deleteTask(Long id);
}
