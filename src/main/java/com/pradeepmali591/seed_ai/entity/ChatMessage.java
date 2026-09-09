package com.pradeepmali591.seed_ai.entity;

import com.pradeepmali591.seed_ai.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    Long id;
    ChatSession chatSession;
    String content;
    MessageRole role;
    String toolCalls; //JSON Array of tools called
    Integer tokensUsed;

    Instant createdAt;
}
