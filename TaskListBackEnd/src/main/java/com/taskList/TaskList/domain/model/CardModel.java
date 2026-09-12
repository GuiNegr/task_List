package com.taskList.TaskList.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "card_model")
//TODO: a classe cardModel é necessaria para agrupar as tasks em tipos de kanban, essa feature vai ficar em holdin para termos o MVP
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
}
