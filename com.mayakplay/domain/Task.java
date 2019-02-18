package com.mayakplay.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Mayakplay on 17.02.2019.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String taskDescription;

    private boolean isFinished;

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;

    public Task() {
    }

    public Task(String taskDescription, boolean isFinished, User owner) {
        this.taskDescription = taskDescription;
        this.isFinished = isFinished;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                isFinished == task.isFinished &&
                Objects.equals(taskDescription, task.taskDescription) &&
                Objects.equals(owner, task.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskDescription, isFinished, owner);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskDescription='" + taskDescription + '\'' +
                ", isFinished=" + isFinished +
                ", owner=" + owner +
                '}';
    }
}
