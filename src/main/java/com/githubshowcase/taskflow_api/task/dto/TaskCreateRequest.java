package com.githubshowcase.taskflow_api.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

public record TaskCreateRequest(
    @NotBlank @Size(max = 150) String title,
    @Size(max = 1000) String description,
    OffsetDateTime dueAt
) { }