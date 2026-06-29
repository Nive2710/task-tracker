package com.nivethitha.backend.repository;

import com.nivethitha.backend.entity.Priority;
import com.nivethitha.backend.entity.Task;
import com.nivethitha.backend.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    Page<Task> findByPriority(Priority priority, Pageable pageable);
}