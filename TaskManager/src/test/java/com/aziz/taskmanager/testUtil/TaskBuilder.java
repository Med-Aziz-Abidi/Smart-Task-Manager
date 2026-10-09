package com.aziz.taskmanager.testUtil;

import com.aziz.taskmanager.domain.Priority;
import com.aziz.taskmanager.domain.Task;
import com.aziz.taskmanager.domain.TaskStatus;

public class TaskBuilder {
    private long id = 1L;
    private String title = "Learn Spring Boot";
    private String description = "";
    private TaskStatus status = TaskStatus.TODO;
    private Priority priority = Priority.LOW;

    public TaskBuilder withId(long id){
        this.id = id;
        return this;
    }

    public TaskBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public TaskBuilder withDescription(String description){
        this.description = description;
        return this;
    }

//    public TaskBuilder withStatus(TaskStatus status){
//        this.status = status;
//        return this;
//    }

    public TaskBuilder withPriority(Priority priority){
        this.priority = priority;
        return this;
    }

    public Task build(){
        return new Task(id, title, description, priority);
    }


}
