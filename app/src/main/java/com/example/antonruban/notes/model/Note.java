package com.example.antonruban.notes.model;

import java.io.Serializable;
import java.util.Date;

/**
 @author antonruban on 26.01.2018.
 */

public class Note implements Serializable {
    private int id;
    private String title = "";
    private String description = "";

    public Note() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
