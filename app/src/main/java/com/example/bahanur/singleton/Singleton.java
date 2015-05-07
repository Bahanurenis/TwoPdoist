package com.example.bahanur.singleton;

import com.example.bahanur.model.Notes;

/**
 * Created by Bahanur on 5/7/2015.
 */
public class Singleton {
    private static Singleton instance = null;
    private Boolean update = false;
    private Notes note = null;

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public Notes getNote() {
        return note;
    }

    public void setNote(Notes note) {
        this.note = note;
    }

    public Boolean isUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }
}
