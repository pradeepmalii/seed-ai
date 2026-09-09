package com.pradeepmali591.seed_ai.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {

    Long id;
    String name;
    String stripePriceId;
    Integer maxProjects;
    Integer maxTokensPerDay;
    Integer maxPreview; //max no of preview allowed per plan
    Boolean unlimitedAi;  //unlimited access to LLM, ignore maxTokenPerDay if true

    Boolean active;

}