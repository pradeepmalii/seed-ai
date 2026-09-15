package com.pradeepmali591.seed_ai.service.impl;

import com.pradeepmali591.seed_ai.dto.member.request.InviteMemeberRequest;
import com.pradeepmali591.seed_ai.dto.member.request.UpdateMemberRoleRequest;
import com.pradeepmali591.seed_ai.dto.member.response.MemberResponse;
import com.pradeepmali591.seed_ai.entity.Project;
import com.pradeepmali591.seed_ai.entity.ProjectMember;
import com.pradeepmali591.seed_ai.entity.ProjectMemberId;
import com.pradeepmali591.seed_ai.entity.User;
import com.pradeepmali591.seed_ai.mapper.ProjectMemberMapper;
import com.pradeepmali591.seed_ai.repository.ProjectMemberRepository;
import com.pradeepmali591.seed_ai.repository.ProjectRepository;
import com.pradeepmali591.seed_ai.repository.UserRepository;
import com.pradeepmali591.seed_ai.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    private final UserRepository userRepository;


    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        List<MemberResponse> memberResponsesList = new ArrayList<>();
        memberResponsesList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        memberResponsesList.addAll(
        projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList());

        return memberResponsesList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, Long userId, InviteMemeberRequest request) {
        Project project = getAccessibleProjectById(projectId, userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }

        User invitee = userRepository.findByEmail(request.email())
                .orElseThrow();

        if(invitee.getId().equals(userId)){
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());
        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Cannot invite once again");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public MemberResponse updateMemberRole(Long memberId, Long projectId, Long userId, UpdateMemberRoleRequest request)
    {
        return null;
    }

    @Override
    public Void deleteProjectMember(Long memberId, Long projectId, Long userId) {
        return null;
    }


    /// INTERNAL FUNCTION
    public Project getAccessibleProjectById(Long projectId, Long userId){
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow();
    }
}
