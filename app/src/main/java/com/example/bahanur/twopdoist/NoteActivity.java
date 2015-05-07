package com.example.bahanur.twopdoist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.bahanur.adapters.NoteAdapter;
import com.example.bahanur.dao.NoteCategoryDao;
import com.example.bahanur.dao.NoteDao;
import com.example.bahanur.model.Notes;
import com.example.bahanur.singleton.Singleton;

import java.sql.SQLException;
import java.util.List;


public class NoteActivity extends Activity {

    ListView noteListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        try{
             noteListView = (ListView) findViewById(R.id.noteList);
            NoteAdapter adapter  = getNoteCategories();
            noteListView.setAdapter(adapter);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Button button=(Button)findViewById(R.id.addnotebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NotEditActivity.class);
                startActivity(intent);
            }
        });

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Singleton.getInstance().setNote((Notes) parent.getItemAtPosition(position));
                Singleton.getInstance().setUpdate(true);
                Intent myIntent = new Intent(NoteActivity.this, NotEditActivity.class);
                startActivity(myIntent);
            }
        });
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

    private  NoteAdapter getNoteCategories() throws SQLException {
        NoteDao noteDao = new NoteDao(getApplicationContext());
        List<Notes> noteList = noteDao.getNotes(Singleton.getInstance().getCategories().toString()); //burada work shopping diye verdim
        NoteAdapter adapter = new  NoteAdapter(this,noteList);
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
