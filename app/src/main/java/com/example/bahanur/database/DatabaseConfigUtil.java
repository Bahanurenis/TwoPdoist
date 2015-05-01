package com.example.bahanur.database;

import com.example.bahanur.model.NoteCategory;
import com.example.bahanur.model.Notes;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Bahanur on 5/1/2015.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?> [] classes=new Class[]{
            NoteCategory.class,
            Notes.class
    };

    public static void main(String [] args) {

        try {
            writeConfigFile("ormlite_config.txt",classes);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
