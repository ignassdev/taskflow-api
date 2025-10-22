package com.githubshowcase.taskflow_api.task;


import com.githubshowcase.taskflow_api.task.dto.TaskCreateRequest;
import com.githubshowcase.taskflow_api.task.dto.TaskResponse;
import com.githubshowcase.taskflow_api.task.dto.TaskUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("api/v1/tasls") @RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@RequestBody @Valid TaskCreateRequest req){
        return service.create(req);
    }

    @GetMapping("/{id}")
    public TaskResponse get(@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping
    public List<TaskResponse> list(){
        return service.list();
    }

    @PatchMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @RequestBody @Valid TaskUpdateRequest req){
        return service.update(id, req);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}