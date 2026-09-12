package com.pradeepmali591.seed_ai.dto.project.response;

import com.pradeepmali591.seed_ai.dto.auth.response.UserProfileResponse;

import java.time.Instant;


public record ProjectResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt,
        UserProfileResponse owner
) {
}
