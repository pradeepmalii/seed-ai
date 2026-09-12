package com.pradeepmali591.seed_ai.service;

import com.pradeepmali591.seed_ai.dto.project.request.ProjectRequest;
import com.pradeepmali591.seed_ai.dto.project.response.ProjectResponse;
import com.pradeepmali591.seed_ai.dto.project.response.ProjectSummaryResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectService {

    List<ProjectSummaryResponse> getUserProjects(Long userId);

    ProjectResponse getUserProjectById(Long id, Long userId);

    ProjectResponse createProject(ProjectRequest request, Long userId);

    ProjectResponse updateProject(Long id, Long userId, @Valid ProjectRequest request);

    void sofDelete(Long id, Long userId);
}
