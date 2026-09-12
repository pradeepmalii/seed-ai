package com.pradeepmali591.seed_ai.dto.project.response;

import java.util.List;

public record FileTreeResponse(
        List<FileNode> files
) {
}
