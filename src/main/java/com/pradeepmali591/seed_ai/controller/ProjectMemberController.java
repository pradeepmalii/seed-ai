package com.pradeepmali591.seed_ai.controller;

import com.pradeepmali591.seed_ai.dto.member.request.InviteMemeberRequest;
import com.pradeepmali591.seed_ai.dto.member.request.UpdateMemberRoleRequest;
import com.pradeepmali591.seed_ai.dto.member.response.MemberResponse;
import com.pradeepmali591.seed_ai.service.ProjectMemberService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMemberController {


    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId){
        Long userId = 1L; //instead of MemberResponse he has put ProjectMember
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(@PathVariable Long projectId,
                                                       @RequestBody @Valid InviteMemeberRequest request){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectMemberService.inviteMember(projectId, userId, request));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(@PathVariable Long memberId,
                                                           @PathVariable Long projectId,
                                                           @RequestBody @Valid UpdateMemberRoleRequest request){
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(memberId, projectId, userId, request));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long memberId,
                                                    @PathVariable Long projectId){
        Long userId = 1L;
        projectMemberService.deleteProjectMember(memberId, projectId, userId);
        return ResponseEntity.noContent().build();
    }


}
