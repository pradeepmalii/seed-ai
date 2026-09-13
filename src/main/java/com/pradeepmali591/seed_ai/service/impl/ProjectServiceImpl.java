package com.pradeepmali591.seed_ai.service.impl;

import com.pradeepmali591.seed_ai.dto.project.request.ProjectRequest;
import com.pradeepmali591.seed_ai.dto.project.response.ProjectResponse;
import com.pradeepmali591.seed_ai.dto.project.response.ProjectSummaryResponse;
import com.pradeepmali591.seed_ai.entity.Project;
import com.pradeepmali591.seed_ai.entity.User;
import com.pradeepmali591.seed_ai.mapper.ProjectMapper;
import com.pradeepmali591.seed_ai.repository.ProjectRepository;
import com.pradeepmali591.seed_ai.repository.UserRepository;
import com.pradeepmali591.seed_ai.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();
        project = projectRepository.save(project);

        return projectMapper.toProjectResponse(project);

    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(projectMapper::toProjectSummaryResponse)
//                .collect(Collectors.toList());

        var project = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(project);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = projectRepository.findAccessibleProjectById(id, userId)
                .orElseThrow();

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, Long userId, ProjectRequest request) {
        return null;
    }

    @Override
    public void sofDelete(Long id, Long userId) {

    }
}
