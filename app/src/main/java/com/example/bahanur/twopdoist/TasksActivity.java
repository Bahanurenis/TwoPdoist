package com.example.bahanur.twopdoist;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bahanur.Alarm.AlarmController;
import com.example.bahanur.common.AppConst;
import com.example.bahanur.common.OnDataSourceChangeListener;
import com.example.bahanur.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TasksActivity extends Activity implements OnDataSourceChangeListener{
    static final int GET_TASK_REQUEST = 2;
    static final int EDIT_TASK_REQUEST = 3;
    private MainController mainController;
    private AlarmController alarmController;
    private TaskListBaseAdapter adapter;
    private String categoryName;
    private static final String TAG = "TasksActivity";
    private static boolean hasGeoFence;
    private static boolean edited;
    private PendingIntent mGeofencePendingIntent;
    private int geoFenceId;
    private String geoFenceLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4285f4"));
        mainController = new MainController(this);
        alarmController = new AlarmController(this);
        mainController.registerOnDataSourceChanged(this);
        hasGeoFence = false;
        edited = false;
        ListView notCompletedTaskLv = (ListView) findViewById(R.id.taskListViewNotCompleted);
        Intent intent = getIntent(); // getting the category that passed from ListMainActivity
        categoryName = intent.getStringExtra(AppConst.CATEGORY_TO_ADD_TASK);
        if (notCompletedTaskLv != null) {
            adapter = new TaskListBaseAdapter(this,mainController.getNotCompletedTasks(categoryName));
            notCompletedTaskLv.setAdapter(adapter);
            notCompletedTaskLv.setOnTouchListener(new OnSwipeTouchListener(this,notCompletedTaskLv){
                public void onSwipeLeft(int position) { //delete task
                    Task t = (Task) adapter.getItem(position);
                    mainController.taskDeleteConfirmation(t); //controller will handle what happens next
                }
                public void onSwipeRight(int position) { // mark task as done
                    Task t = (Task) adapter.getItem(position);
                    mainController.taskCompleteConfirmation(t);
                }

            });

            notCompletedTaskLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Task t = (Task) adapter.getItem(position);
                    Intent editIntent = new Intent(getBaseContext(),EditTaskActivity.class);
                    editIntent.putExtra(AppConst.TASK_ID_FOR_INTENT,t.getId());
                    editIntent.putExtra(AppConst.TASK_NAME_FOR_INTENT,t.getTaskName());
                    editIntent.putExtra(AppConst.TASK_NOTE_FOR_INTENT,t.getTaskNote());
                    editIntent.putExtra(AppConst.TASK_PRIORITY_FOR_INTENT,t.getPriority());
                    editIntent.putExtra(AppConst.WHEN_ALARM,t.getTimeToAlarm());
                    editIntent.putExtra(AppConst.WHERE_ALARM,t.getTaskLocation());
                    startActivityForResult(editIntent, EDIT_TASK_REQUEST);
                }
            });

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_add:
                Intent addIntent = new Intent(this, AddTaskActivity.class);
                addIntent.putExtra(AppConst.CATEGORY_TO_ADD_TASK,categoryName);
                startActivityForResult(addIntent, GET_TASK_REQUEST);
                return true;
            case R.id.action_see_completed:
                Intent completedIntent = new Intent(this,CompletedTasksActivity.class);
                startActivity(completedIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == GET_TASK_REQUEST) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String taskName = extras.getString(AppConst.TASK_NAME_FOR_INTENT);
                String taskNote = extras.getString(AppConst.TASK_NOTE_FOR_INTENT);
                int taskPriority = extras.getInt(AppConst.TASK_PRIORITY_FOR_INTENT);
                long timeToAlarm = extras.getLong(AppConst.WHEN_ALARM);
                String taskLocation = extras.getString(AppConst.WHERE_ALARM);
                Task t = new Task();
                t.setTaskName(taskName);
                t.setTaskNote(taskNote);
                t.setPriority(taskPriority);
                t.setCategory(categoryName);
                t.setCompleted(0); //not completed when creating it
                t.setTimeToAlarm(timeToAlarm);
                t.setTaskLocation(taskLocation);
                int id = mainController.addTask(t,categoryName);
                t.setId(id);
                geoFenceId = t.getId();
                geoFenceLocation = t.getTaskLocation();
                if (t.getTimeToAlarm() > 0 ) {
                    alarmController.CreateAlarm(t.getTaskName(), t.getTimeToAlarm(), t.getId());
                }
                if (geoFenceLocation != null && !geoFenceLocation.equals("")) {
                    hasGeoFence = true;
                }
            }
        }
        else if (resultCode == RESULT_OK && requestCode == EDIT_TASK_REQUEST) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                int id = extras.getInt(AppConst.TASK_ID_FOR_INTENT);
                String newTaskName = extras.getString(AppConst.TASK_NAME_FOR_INTENT);
                String newTaskNote = extras.getString(AppConst.TASK_NOTE_FOR_INTENT);
                int newTaskPriority = extras.getInt(AppConst.TASK_PRIORITY_FOR_INTENT);
                long newTimeToAlarm = extras.getLong(AppConst.WHEN_ALARM);
                String newTaskLocation = extras.getString(AppConst.WHERE_ALARM);
                Task t = new Task();
                t.setId(id);
                t.setTaskName(newTaskName);
                t.setTaskNote(newTaskNote);
                t.setPriority(newTaskPriority);
                t.setCategory(categoryName);
                t.setCompleted(0); //not completed when editing it
                t.setTimeToAlarm(newTimeToAlarm);
                t.setTaskLocation(newTaskLocation);
                geoFenceLocation = newTaskLocation;
                mainController.editTask(t);
                if (t.getTimeToAlarm() > 0) {
                    alarmController.cancelAlarm(t.getId());
                    alarmController.CreateAlarm(t.getTaskName(),t.getTimeToAlarm(),t.getId());
                }
                if (geoFenceLocation != null && !geoFenceLocation.equals("")) {
                    edited = true;
                    hasGeoFence = true;
                }
            }
        }
    }

    @Override
    public void DataSourceChanged() {
        if (adapter != null) {
            adapter.UpdateDataSource(mainController.getNotCompletedTasks(categoryName));
            adapter.notifyDataSetChanged();
        }
    }



    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }



}
