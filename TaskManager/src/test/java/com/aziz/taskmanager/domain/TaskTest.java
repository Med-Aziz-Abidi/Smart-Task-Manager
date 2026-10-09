package com.aziz.taskmanager.domain;

import com.aziz.taskmanager.testUtil.TaskBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TaskTest {
    @Test
    @DisplayName("With new Task, the status should be TODO")
    void createNewTask(){
        Task task = new Task(1L, "Learn Spring Boot", "", Priority.HIGH);

        assertThat(task.getStatus()).isEqualTo(TaskStatus.TODO);
    }

    @Test
    @DisplayName("When creating a new task with null title, an Exception will be thrown")
    void createNewTaskWithNullTitleShouldThrowException(){
        assertThatThrownBy(() -> new TaskBuilder()
                .withTitle(null)
                .build()).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be blank");
    }

    @Test
    @DisplayName("When creating a new task with empty title, an Exception will be thrown")
    void createNewTaskWithEmptyTitleShouldThrowException(){
        assertThatThrownBy(() -> new TaskBuilder()
                .withTitle("")
                .build()).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be blank");
    }

    @Test
    @DisplayName("When creating a new task with blank title, an Exception will be thrown")
    void createNewTaskWithBlankTitleShouldThrowException(){
        assertThatThrownBy(() -> new TaskBuilder()
                .withTitle("   ")
                .build()).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be blank");
    }

    @Test
    void nullDescriptionShouldDefaultToEmptyString() {
        Task task = new TaskBuilder()
                .withDescription(null)
                .build();

        assertThat(task.getDescription()).isEmpty();
    }

    @Test
    void nullPriorityShouldDefaultToLow() {
        Task task = new TaskBuilder()
                .withPriority(null)
                .build();

        assertThat(task.getPriority()).isEqualTo(Priority.LOW);
    }

    @Test
    void negativeIdShouldThrowException() {
        assertThatThrownBy(() -> new TaskBuilder()
                .withId(-1L)
                .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be negative");
    }

    @Test
    void zeroIdShouldBeAllowed() {
        Task task = new TaskBuilder()
                .withId(0L)
                .build();

        assertThat(task.getId()).isZero();
    }


    @Test
    @DisplayName("when using start, the status should be changed to InProgress")
    void startShouldChangeStatusToInProgress(){
        Task task = new TaskBuilder()
                .build();

        task.start();

        assertThat(task.getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Test
    @DisplayName("when using complete, the status should be changed to DONE")
    void completeShouldChangeStatusToDone(){
        Task task = new TaskBuilder()
                .build();

        task.start();
        task.complete();

        assertThat(task.getStatus()).isEqualTo(TaskStatus.DONE);
    }

    @Test
    @DisplayName("when using reopen, the status should be changed to TODO")
    void reopenShouldChangeStatusToTodo(){
        Task task = new TaskBuilder()
                .build();

        task.start();
        task.complete();
        task.reopen();

        assertThat(task.getStatus()).isEqualTo(TaskStatus.TODO);
    }

    @Test
    @DisplayName("when using complete for a newly created task, an exception should be thrown")
    void completeNewlyCreatedTaskShouldThrowException(){
        Task task = new TaskBuilder()
                .build();

        assertThatThrownBy(task::complete)
                .isInstanceOf(IllegalStateException.class);
        assertThat(task.getStatus()).isEqualTo(TaskStatus.TODO);
    }

    @Test
    @DisplayName("when using reopen for a finished task, an exception should be thrown")
    void reopenFinishedTaskShouldThrowException(){
        Task task = new TaskBuilder()
                .build();

        task.start();

        assertThatThrownBy(task::reopen)
                .isInstanceOf(IllegalStateException.class);
        assertThat(task.getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Test
    @DisplayName("when using reopen for an in progress task, an exception should be thrown")
    void reopenStartedTaskShouldThrowException(){
        Task task = new TaskBuilder()
                .build();

        assertThatThrownBy(task::reopen)
                .isInstanceOf(IllegalStateException.class);
        assertThat(task.getStatus()).isEqualTo(TaskStatus.TODO);
    }

    @Test
    @DisplayName("when using start for an in progress created task, an exception should be thrown")
    void startInProgressTaskShouldThrowException(){
        Task task = new TaskBuilder()
                .build();
        task.start();

        assertThatThrownBy(task::start)
                .isInstanceOf(IllegalStateException.class);
        assertThat(task.getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Test
    @DisplayName("when using start for a finished task, an exception should be thrown")
    void startDoneTaskShouldThrowException(){
        Task task = new TaskBuilder()
                .build();
        task.start();
        task.complete();

        assertThatThrownBy(task::start)
                .isInstanceOf(IllegalStateException.class);
        assertThat(task.getStatus()).isEqualTo(TaskStatus.DONE);
    }

    @Test
    @DisplayName("when using complete for a finished task, an exception should be thrown")
    void completeDoneTaskShouldThrowException(){
        Task task = new TaskBuilder()
                .build();
        task.start();
        task.complete();

        assertThatThrownBy(task::complete)
                .isInstanceOf(IllegalStateException.class);
        assertThat(task.getStatus()).isEqualTo(TaskStatus.DONE);
    }










}
