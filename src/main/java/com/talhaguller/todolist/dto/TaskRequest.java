package com.talhaguller.todolist.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TaskRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 250)
    private String description;

    private boolean completed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
