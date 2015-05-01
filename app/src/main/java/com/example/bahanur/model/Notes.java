package com.example.bahanur.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.Date;

/**
 * Created by Bahanur on 5/1/2015.
 */
public class Notes {

    @DatabaseField (generatedId = true)
    Integer id;
    @DatabaseField
    String title;
    @DatabaseField
    String icerik; //içerik
    @DatabaseField
    Date tarih;
    @DatabaseField
    String zaman;
    @ForeignCollectionField
    ForeignCollection<NoteCategory> categories;

    public Notes(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }

    public ForeignCollection<NoteCategory> getCategories() {
        return categories;
    }

    public void setCategories(ForeignCollection<NoteCategory> categories) {
        this.categories = categories;
    }


}
