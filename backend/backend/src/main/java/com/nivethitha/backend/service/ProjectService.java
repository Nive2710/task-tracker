package com.nivethitha.backend.service;

import com.nivethitha.backend.dto.ProjectRequestDto;
import com.nivethitha.backend.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {

    ProjectResponseDto createProject(ProjectRequestDto request);

    List<ProjectResponseDto> getAllProjects();

    ProjectResponseDto getProjectById(Long id);

    ProjectResponseDto updateProject(Long id, ProjectRequestDto request);

    void deleteProject(Long id);
}