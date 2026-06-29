package com.nivethitha.backend.service;

import com.nivethitha.backend.dto.TaskRequestDto;
import com.nivethitha.backend.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto request);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto getTaskById(Long id);

    TaskResponseDto updateTask(Long id, TaskRequestDto request);

    void deleteTask(Long id);
}
