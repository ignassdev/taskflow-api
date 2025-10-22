package com.githubshowcase.taskflow_api.task;


import com.githubshowcase.taskflow_api.common.NotFoundException;
import com.githubshowcase.taskflow_api.task.dto.TaskCreateRequest;
import com.githubshowcase.taskflow_api.task.dto.TaskResponse;
import com.githubshowcase.taskflow_api.task.dto.TaskUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repo;

    @Transactional
    public TaskResponse create(TaskCreateRequest req){
        var e = TaskEntity.builder()
                .title(req.title())
                .description(req.description())
                .dueAt(req.dueAt())
                .build();
        return map(repo.save(e));
    }

    public TaskResponse get(Long id){
        return repo.findById(id).map(this::map)
        .orElseThrow(() -> new NotFoundException("Task %d not found".formatted(id)));
    }

    public List<TaskResponse> list(){
        return repo.findAll().stream().map(this::map).toList();
    }

    @Transactional
    public TaskResponse update(Long id, TaskUpdateRequest req){
        var e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Task %d not found".formatted(id)));
        if (req.title() != null) e.setTitle(req.title());
        if (req.description() != null) e.setDescription(req.description());
        if (req.status() != null) e.setStatus(req.status());
        if (req.dueAt() != null) e.setDueAt(req.dueAt());
        return map(repo.save(e));
    }

    @Transactional
    public void delete(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Task %d not found".formatted(id));
        repo.deleteById(id);
    }

    private TaskResponse map(TaskEntity e){
        return new TaskResponse(e.getId(), e.getTitle(), e.getDescription(),
                e.getStatus(), e.getDueAt(), e.getCreatedAt());
    }
}