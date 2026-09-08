package com.taskList.TaskList.domain.model;


import com.taskList.TaskList.domain.dto.LogDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Table(name = "Log")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    //TODO: deixar o log associado a uma task
    private TaskModel task;


    //Dto Construct
    public LogModel(String description, LocalDate date, LocalDate date1) {
        this.description = description;
        this.createdAt = date;
        this.updatedAt = date1;
    }


    public static LogModel toModel (LogDTO log) {
        return new LogModel(log.description(),log.createdAt(),log.updatedAt());
    }

}
