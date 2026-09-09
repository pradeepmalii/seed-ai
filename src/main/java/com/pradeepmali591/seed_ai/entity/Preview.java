package com.pradeepmali591.seed_ai.entity;

import com.pradeepmali591.seed_ai.enums.PreviewStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preview {

    Long id;
    Project project;

    PreviewStatus status;

    String namespace;
    String podName;
    String previewUrl;

    Instant startedAt;
    Instant terminatedAt;

    Instant createdAt;
}
