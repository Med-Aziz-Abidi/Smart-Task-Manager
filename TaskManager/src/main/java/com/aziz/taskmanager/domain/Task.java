package com.aziz.taskmanager.domain;

public class Task {
    private final long id;
    private final String title;
    private final String description;
    private TaskStatus status = TaskStatus.TODO;
    private final Priority priority;

    public Task(long id, String title, String description, Priority priority) {
        if (id < 0)
            throw new IllegalArgumentException("ID cannot be negative");

        this.id = id;
        this.title = validateTitle(title);
        this.description = description == null ? "" : description;
        this.priority = priority == null ? Priority.LOW : priority;
    }

    public long getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    private String validateTitle(String title){
        if (title == null || title.isBlank() ){
            throw  new IllegalArgumentException("Title cannot be blank");
        }
        return title;
    }

    public void start(){
        if (status != TaskStatus.TODO)
            throw new IllegalStateException();
        this.status = TaskStatus.IN_PROGRESS;
    }
    public void complete(){
        if (status != TaskStatus.IN_PROGRESS)
            throw new IllegalStateException();
        this.status = TaskStatus.DONE;
    }
    public void reopen(){
        if (status != TaskStatus.DONE)
            throw new IllegalStateException();
        this.status = TaskStatus.TODO;
    }
}
