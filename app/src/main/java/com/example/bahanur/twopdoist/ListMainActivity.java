package com.example.bahanur.twopdoist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bahanur.common.AppConst;
import com.example.bahanur.common.OnDataSourceChangeListener;
import com.example.bahanur.model.Category;


public class ListMainActivity extends Activity implements OnDataSourceChangeListener {
    static final int GET_CATEGORY_REQUEST = 1;
    private MainController controller;
    private CategoryListBaseAdapter adapter;
    private TextView addSomethingTv;
    private static final String TAG = "ListMainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get a Tracker (should auto-report)
        setContentView(R.layout.activity_list_main);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4285f4"));
        controller = new MainController(this);
        controller.registerOnDataSourceChanged(this);
        ListView categoryLv = (ListView) findViewById(R.id.categoryListView);
        addSomethingTv = (TextView)findViewById(R.id.addSomething);
        addSomethingTv.setVisibility(View.INVISIBLE);
        if (categoryLv != null) {
            adapter = new CategoryListBaseAdapter(this, controller.getAllCategories());
            categoryLv.setAdapter(adapter);
            categoryLv.setOnTouchListener(new OnSwipeTouchListener(this,categoryLv){
                public void onSwipeLeft(int position) { //delete category and all the tasks inside it
                    Category c = (Category) adapter.getItem(position);
                    controller.categoryDeleteConfirmation(c);
                }
            });

            categoryLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Category c = (Category)adapter.getItem(position);
                    Intent categoryTasksIntent = new Intent(getBaseContext(), TasksActivity.class);
                    categoryTasksIntent.putExtra(AppConst.CATEGORY_TO_ADD_TASK,c.getCategoryName()); // add category to the intent to know where to add the activity
                    startActivity(categoryTasksIntent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_see_completed:
                Intent completedIntent = new Intent(this,CompletedTasksActivity.class);
                startActivity(completedIntent);
                return true;
            case R.id.action_add:
                Intent addIntent = new Intent(this, AddCategoryActivity.class);
                startActivityForResult(addIntent, GET_CATEGORY_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == GET_CATEGORY_REQUEST) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String categoryName = extras.getString(AppConst.CATEGORY_NAME_FOR_INTENT);
                Category c = new Category();
                c.setCategoryName(categoryName);
                controller.addCategory(c);
            }
        }
    }


    @Override
    public void DataSourceChanged() {
        if (adapter != null) {
            adapter.UpdateDataSource(controller.getAllCategories());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        //Stop the analytics tracking

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Get an Analytics tracker to report app starts & uncaught exceptions etc.

    }
}
