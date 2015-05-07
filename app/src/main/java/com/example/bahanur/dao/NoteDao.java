package com.example.bahanur.dao;

import android.content.Context;

import com.example.bahanur.database.DatabaseHelper;
import com.example.bahanur.model.Notes;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Bahanur on 5/3/2015.
 */
public class NoteDao {
    DatabaseHelper helper;
    RuntimeExceptionDao<Notes,Integer> noteDao;

    public NoteDao(Context context){
        helper=new DatabaseHelper(context);
        helper= OpenHelperManager.getHelper(context, DatabaseHelper.class);
        noteDao=helper.getNoteRuntimeExceptionDao();
    }

    public void addNote(Notes note){
        try {
            noteDao.create(note);
            OpenHelperManager.releaseHelper();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void updateNote(Notes note) throws SQLException {
        noteDao.update(note);
        OpenHelperManager.releaseHelper();
    }

    public List<Notes> getNotes(String cotegorie) throws SQLException {

        List<Notes> notesList = noteDao.queryForAll();
        return notesList;
    }
}
