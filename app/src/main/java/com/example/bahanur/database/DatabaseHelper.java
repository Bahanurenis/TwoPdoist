package com.example.bahanur.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bahanur.model.NoteCategory;
import com.example.bahanur.model.Notes;
import com.example.bahanur.twopdoist.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Bahanur on 5/1/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME="TWOPDOIST";
    private  static final int DATABASE_VERSION=1;

    private Dao<NoteCategory,Integer> noteCategoryDao=null;
    private RuntimeExceptionDao<NoteCategory,Integer> noteCategoryRuntimeExceptionDao=null;

    private Dao<Notes,Integer> noteDao=null;//Note için dao oluşturdum
    private RuntimeExceptionDao<Notes,Integer> noteRuntimeExceptionDao=null;//Run time exception oluşturdum

    public  DatabaseHelper(Context context ){
        super(context,DATABASE_NAME,null,DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try{

            Log.i(DatabaseHelper.class.getName(),"onCreate");
            TableUtils.createTable(connectionSource,Notes.class);
            TableUtils.createTable(connectionSource, NoteCategory.class);

        }
        catch (SQLException ex){
            Log.e(DatabaseHelper.class.getName(),"can not create database");
            throw new RuntimeException(ex);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        Log.i(DatabaseHelper.class.getName(),"onUprage");
        try {
            TableUtils.dropTable(connectionSource,Notes.class,true);
            TableUtils.dropTable(connectionSource,NoteCategory.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getName(), "can not drop database");
            throw new RuntimeException(ex);

        }

    }

    public Dao<NoteCategory,Integer> getNoteCategoryDao() throws SQLException {
        if(noteCategoryDao==null)
        {
            noteCategoryDao=getDao(NoteCategory.class);
        }
        return noteCategoryDao;
    }



    public RuntimeExceptionDao<NoteCategory,Integer> getNoteCategoryRuntimeExceptionDao(){
        if(noteCategoryRuntimeExceptionDao==null)
        {
            noteCategoryRuntimeExceptionDao=getRuntimeExceptionDao(NoteCategory.class);
        }
        return noteCategoryRuntimeExceptionDao;
    }

    public Dao<Notes,Integer> getNotesDao() throws SQLException{
        if(noteDao==null){
            noteDao=getDao(Notes.class);
        }
        return noteDao;
    }

    public  RuntimeExceptionDao<Notes,Integer>getNoteRuntimeExceptionDao(){
        if(noteRuntimeExceptionDao==null){
            noteRuntimeExceptionDao=getRuntimeExceptionDao(Notes.class); //parametre verince hata veriyo
        }
        return noteRuntimeExceptionDao;
    }

}
