package com.example.bahanur.dao;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.bahanur.database.DatabaseHelper;
import com.example.bahanur.model.NoteCategory;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

/**
 * Created by yoda on 2.5.2015.
 */
public class NoteCategoryDao {
    DatabaseHelper helper;
    RuntimeExceptionDao<NoteCategory,Integer> noteCategorieDao;

    public NoteCategoryDao(Context context){
        helper=new DatabaseHelper(context);
        helper= OpenHelperManager.getHelper(context, DatabaseHelper.class);
        noteCategorieDao=helper.getNoteCategoryRuntimeExceptionDao();
    }


    public void addNoteCategory(){

        noteCategorieDao.create(new NoteCategory("work"));
        noteCategorieDao.create(new NoteCategory("personal"));
        OpenHelperManager.releaseHelper();

    }

    public List<NoteCategory> getNoteCategories(){
        List<NoteCategory> categories =  noteCategorieDao.queryForAll();
        return categories;
    }

}
