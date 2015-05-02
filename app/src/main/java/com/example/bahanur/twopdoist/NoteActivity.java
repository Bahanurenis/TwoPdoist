package com.example.bahanur.twopdoist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.bahanur.adapters.NoteAdapter;
import com.example.bahanur.dao.NoteCategoryDao;
import com.example.bahanur.model.NoteCategory;

import java.util.List;
import java.util.SimpleTimeZone;


public class NoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        try{
            ListView noteListView = (ListView) findViewById(R.id.noteList);
            NoteAdapter adapter  = getNoteCategories();
            noteListView.setAdapter(adapter);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void insertNoteCategory(){
        NoteCategoryDao dao = new NoteCategoryDao(getApplicationContext());
        dao.addNoteCategory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);

        return true;
    }

    private  NoteAdapter getNoteCategories(){
        NoteCategoryDao noteCategoryDao = new NoteCategoryDao(getApplicationContext());
        List<NoteCategory> noteCategories = noteCategoryDao.getNoteCategories();
        String[] from = new String[]{"name"};
        int[] to = new int[]{R.id.row};
        NoteAdapter adapter = new  NoteAdapter(this,noteCategories);
        adapter.addAll(noteCategories);
        return adapter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
