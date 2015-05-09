package com.example.bahanur.dao;



import com.example.bahanur.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TasksIDAO {

    List<Task> getNotCompletedTasks(String category);
    List<Task> getCompletedTasks();
    void removeTask(Task task);
    Task addTask(Task task, String category);
    void editTask(Task task);
    void markTaskAsDone(Task task);
    void getTaskBack(Task task);
}
