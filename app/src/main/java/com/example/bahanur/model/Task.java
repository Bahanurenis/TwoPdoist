package com.example.bahanur.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by yoda on 9.5.2015.
 */
public class Task {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String taskName;
    @DatabaseField
    private String taskNote;
    @DatabaseField
    private int priority;
    @DatabaseField
    private String category;
    @DatabaseField
    private int completed;
    @DatabaseField
    private long timeToAlarm;
    @DatabaseField
    private String taskLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public long getTimeToAlarm() {
        return timeToAlarm;
    }

    public void setTimeToAlarm(long timeToAlarm) {
        this.timeToAlarm = timeToAlarm;
    }

    public String getTaskLocation() {
        return taskLocation;
    }

    public void setTaskLocation(String taskLocation) {
        this.taskLocation = taskLocation;
    }
}
