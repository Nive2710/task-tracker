package com.nivethitha.backend.controller;

import com.nivethitha.backend.dto.ProjectRequestDto;
import com.nivethitha.backend.dto.ProjectResponseDto;
import com.nivethitha.backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectResponseDto createProject(
            @Valid @RequestBody ProjectRequestDto request) {

        return projectService.createProject(request);
    }

    @GetMapping
    public List<ProjectResponseDto> getAllProjects() {

        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectResponseDto getProjectById(@PathVariable Long id) {

        return projectService.getProjectById(id);
    }

    @PutMapping("/{id}")
    public ProjectResponseDto updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequestDto request) {

        return projectService.updateProject(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {

        projectService.deleteProject(id);
    }
}