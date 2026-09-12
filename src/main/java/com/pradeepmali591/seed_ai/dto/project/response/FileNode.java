package com.pradeepmali591.seed_ai.dto.project.response;

import java.time.Instant;

public record FileNode(
        String path,
        Long size,
        String type,
        Instant modifiedAt
) {
}
