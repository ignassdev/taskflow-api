package com.githubshowcase.taskflow_api.task.dto;

import com.githubshowcase.taskflow_api.task.TaskEntity.Status;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

public record TaskUpdateRequest(
    @Size(max = 150) String title,
    @Size(max = 1000) String description,
    Status status,
    OffsetDateTime dueAt
) { }
