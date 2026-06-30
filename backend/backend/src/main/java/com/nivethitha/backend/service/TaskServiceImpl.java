package com.nivethitha.backend.service;
import org.springframework.data.domain.Sort;

import com.nivethitha.backend.dto.TaskRequestDto;
import com.nivethitha.backend.dto.TaskResponseDto;
import com.nivethitha.backend.entity.Task;
import com.nivethitha.backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.nivethitha.backend.entity.Priority;
import com.nivethitha.backend.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.nivethitha.backend.exception.ResourceNotFoundException;
import com.nivethitha.backend.entity.Project;
import com.nivethitha.backend.repository.ProjectRepository;
import com.nivethitha.backend.dto.TaskStatsDto;
import com.nivethitha.backend.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public TaskResponseDto createTask(TaskRequestDto request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
task.setDueDate(request.getDueDate());
if (request.getProjectId() != null) {

    Project project = projectRepository.findById(
            request.getProjectId())
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Project not found with id: "
                                    + request.getProjectId()));

    task.setProject(project);
}

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    @Override
public Page<TaskResponseDto> getAllTasks(
        int page,
        int size,
        TaskStatus status,
        Priority priority,
        String sortBy,
        String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    Page<Task> tasks;

    if (status != null) {
        tasks = taskRepository.findByStatus(status, pageable);
    } else if (priority != null) {
        tasks = taskRepository.findByPriority(priority, pageable);
    } else {
        tasks = taskRepository.findAll(pageable);
    }

    return new PageImpl<>(
            tasks.getContent()
                    .stream()
                    .map(this::mapToResponse)
                    .toList(),
            pageable,
            tasks.getTotalElements()
    );
}
    @Override
    public TaskResponseDto getTaskById(Long id) {

       Task task = taskRepository.findById(id)
        .orElseThrow(() ->
        new ResourceNotFoundException("Task not found with id: " + id));
        return mapToResponse(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
        new ResourceNotFoundException("Task not found with id: " + id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
task.setDueDate(request.getDueDate());
if (request.getProjectId() != null) {

    Project project = projectRepository.findById(
            request.getProjectId())
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Project not found with id: "
                                    + request.getProjectId()));

    task.setProject(project);
}

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
if (task.getProject() != null) {
    response.setProjectId(task.getProject().getId());
}

        return response;
    }
    @Override
public TaskStatsDto getTaskStatistics() {

    TaskStatsDto stats = new TaskStatsDto();

    stats.setTotalTasks(taskRepository.count());

    stats.setPendingTasks(
            taskRepository.countByStatus(TaskStatus.PENDING));

    stats.setInProgressTasks(
            taskRepository.countByStatus(TaskStatus.IN_PROGRESS));

    stats.setCompletedTasks(
            taskRepository.countByStatus(TaskStatus.COMPLETED));

    return stats;
}
@Override
public Page<TaskResponseDto> searchTasks(
        String keyword,
        int page,
        int size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<Task> tasks =
            taskRepository.findByTitleContainingIgnoreCase(
                    keyword,
                    pageable);

    return new PageImpl<>(
            tasks.getContent()
                    .stream()
                    .map(this::mapToResponse)
                    .toList(),
            pageable,
            tasks.getTotalElements()
    );
}
}