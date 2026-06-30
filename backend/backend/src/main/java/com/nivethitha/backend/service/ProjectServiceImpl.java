package com.nivethitha.backend.service;

import com.nivethitha.backend.dto.ProjectRequestDto;
import com.nivethitha.backend.dto.ProjectResponseDto;
import com.nivethitha.backend.entity.Project;
import com.nivethitha.backend.exception.ResourceNotFoundException;
import com.nivethitha.backend.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto request) {

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());

        return mapToResponse(projectRepository.save(project));
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found with id: " + id));

        return mapToResponse(project);
    }

    @Override
    public ProjectResponseDto updateProject(Long id,
                                            ProjectRequestDto request) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found with id: " + id));

        project.setName(request.getName());
        project.setDescription(request.getDescription());

        return mapToResponse(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long id) {

        projectRepository.deleteById(id);
    }

    private ProjectResponseDto mapToResponse(Project project) {

        ProjectResponseDto response = new ProjectResponseDto();

        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());

        return response;
    }
}