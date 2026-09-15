package com.pradeepmali591.seed_ai.service;

import com.pradeepmali591.seed_ai.dto.member.request.InviteMemeberRequest;
import com.pradeepmali591.seed_ai.dto.member.request.UpdateMemberRoleRequest;
import com.pradeepmali591.seed_ai.dto.member.response.MemberResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectMemberService {

    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, Long userId, InviteMemeberRequest request);

    MemberResponse updateMemberRole(Long memberId, Long projectId, Long userId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long memberId, Long projectId, Long userId);
}
