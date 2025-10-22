package com.githubshowcase.taskflow_api.task.dto;

import com.githubshowcase.taskflow_api.task.TaskEntity.Status;

import java.time.OffsetDateTime;

public record TaskResponse(
   Long id, String title, String description,
   Status status, OffsetDateTime dueAt, OffsetDateTime createdAt
) {}
