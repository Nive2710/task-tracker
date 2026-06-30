package com.nivethitha.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class ProjectRequestDto {

    @NotBlank(message = "Project name is required")
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}