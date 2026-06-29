package com.nivethitha.backend.service;

import com.nivethitha.backend.dto.TaskRequestDto;
import com.nivethitha.backend.dto.TaskResponseDto;
import com.nivethitha.backend.entity.Task;
import com.nivethitha.backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDto createTask(TaskRequestDto request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
task.setDueDate(request.getDueDate());

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return mapToResponse(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
task.setDueDate(request.getDueDate());

        Task updatedTask = taskRepository.save(task);

        return mapToResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {

        taskRepository.deleteById(id);
    }

    private TaskResponseDto mapToResponse(Task task) {

        TaskResponseDto response = new TaskResponseDto();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
response.setDueDate(task.getDueDate());

        return response;
    }
}