package com.example.bahanur.twopdoist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bahanur.dao.NoteDao;
import com.example.bahanur.model.Notes;

import java.util.Date;


public class NotEditActivity extends Activity {

    public static int numTitle = 1;
    public static String curDate = "";
    public static String curText = "";
    private EditText mTitleText;
    private EditText mBodyText;
    private Button saveButton;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_edit);
        mTitleText = (EditText)findViewById(R.id.noteTitle);
        mBodyText = (EditText) findViewById(R.id.notedesc);
        saveButton = (Button) findViewById(R.id.savenote);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notes note = new Notes();
                note.setTitle(mTitleText.getText().toString());
                note.setIcerik(mBodyText.getText().toString());
                note.setTarih(new Date());
                note.setCategorie("work");
                note.setZaman("Deneme");
                NoteDao dao = new NoteDao(getApplicationContext());
                dao.addNote(note);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_not_edit, menu);
        return true;
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

