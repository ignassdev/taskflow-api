package com.githubshowcase.taskflow_api.task;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity @Table(name = "tasks")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TaskEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Status status;

    private OffsetDateTime dueAt;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist void onCreate(){
        createdAt = OffsetDateTime.now();
        if (status == null) status = Status.TODO;
    }
    public enum Status {TODO, IN_PROGRESS, DONE}

}