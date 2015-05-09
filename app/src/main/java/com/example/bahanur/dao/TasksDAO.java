package com.example.bahanur.dao;

import android.content.Context;
import android.util.Log;

import com.example.bahanur.database.DatabaseHelper;
import com.example.bahanur.model.Task;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

public class TasksDAO implements TasksIDAO {
    private static final String TAG ="TasksDAO";

    DatabaseHelper helper;
    RuntimeExceptionDao<Task,Integer> taskDao;


    public TasksDAO(Context context) {
        helper=new DatabaseHelper(context);
        helper= OpenHelperManager.getHelper(context, DatabaseHelper.class);
        taskDao=helper.getTaskRuntimeExceptionDao();
    }


    @Override
    public Task addTask(Task task, String category) {
        if (task == null)
            return null;

        //do the insert.
        task.setCategory(category);
        int insertId =taskDao.create(task);
        if (insertId == -1) {
            System.out.println("insert id = -1");
        }

        Task newTask = taskDao.queryForId(task.getId());
        OpenHelperManager.releaseHelper();
        return newTask;
    }



    @Override
    public void editTask(Task task) {
        if (task == null)
            return;
       int rowsAffected =  taskDao.update(task);
        if (rowsAffected == 0) {
            Log.e(TAG,"no affect on the database from editTask");
        }

    }



    @Override
    public void removeTask(Task task) {
        taskDao.delete(task);
    }

    @Override
    public List<Task> getNotCompletedTasks(String category) {
        List<Task> tasks = new ArrayList<>();
        tasks = taskDao.queryForEq("completed", 0);
        return tasks;
    }

    @Override
    public List<Task> getCompletedTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks = taskDao.queryForEq("completed", 1);
        return tasks;
    }

    @Override
    public void markTaskAsDone(Task task) {
        task.setCompleted(1);
        taskDao.update(task);

    }

    @Override
    public void getTaskBack(Task task) {
        task.setCompleted(0);
        int rowsAffected = taskDao.update(task);

        if (rowsAffected == 0) {
            Log.e(TAG,"no affect on the database from markAsDone");
        }
    }
}
