package com.example.bahanur.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Bahanur on 5/1/2015.
 */
public class NoteCategory {
    @DatabaseField(generatedId = true)
    Integer id;
    @DatabaseField
    String name;

    public NoteCategory(){
    }

    public NoteCategory(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString()
    {
        return (NoteCategory.class.toString()+id);
    }

}
