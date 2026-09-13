package com.pradeepmali591.seed_ai.mapper;

import com.pradeepmali591.seed_ai.dto.project.response.ProjectResponse;
import com.pradeepmali591.seed_ai.dto.project.response.ProjectSummaryResponse;
import com.pradeepmali591.seed_ai.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> project);
}
