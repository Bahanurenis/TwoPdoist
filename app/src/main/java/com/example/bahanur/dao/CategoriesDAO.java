package com.example.bahanur.dao;

import android.content.Context;

import com.example.bahanur.database.DatabaseHelper;
import com.example.bahanur.model.Category;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO implements CategoriesIDAO{
    private static final String TAG ="CategoriesDAO";
    DatabaseHelper helper;
    RuntimeExceptionDao<Category,Integer> categoryDao;

    public CategoriesDAO(Context context) {
        helper=new DatabaseHelper(context);
        helper= OpenHelperManager.getHelper(context, DatabaseHelper.class);
        categoryDao=helper.getCategoryRuntimeExceptionDao();
}
    @Override
    public Category addCategory(Category category) {

        if (category == null)
            return null;
        //build the content values.

        int insertId = categoryDao.create(category);

        if (insertId == -1) {
            System.out.println("insert id = -1");
        }

       Category newCategory = categoryDao.queryForEq("categoryName", category.getCategoryName()).get(0);
        OpenHelperManager.releaseHelper();
        return newCategory;
    }


    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        categories =  categoryDao.queryForAll();
        return categories;
    }

    @Override
    public void removeCategory(Category category) {
        long id = category.getId();
        categoryDao.delete(category);
    }
}
