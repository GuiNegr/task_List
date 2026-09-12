package com.taskList.TaskList.domain.enums;

import lombok.Getter;


public enum TaskStatusEnum {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    IN_PROGRESS("IN_PROGRESS"),
    IN_WAITING("IN_WAITING"),
    NEW("NEW"),
    ERROR_STATUS("ERROR_STATUS");

    private String name;

     TaskStatusEnum(String name){
        this.name = name;
    }

    public String getEnumValue() {
        return this.name;
    }


    public static TaskStatusEnum getEnumByValue(String value){
         TaskStatusEnum[] values = TaskStatusEnum.values();
         for (TaskStatusEnum taskStatusEnum : values) {
             if(taskStatusEnum.getEnumValue().equals(value)){
                 return taskStatusEnum;
             }
         }
         return TaskStatusEnum.ERROR_STATUS;
    }
}
