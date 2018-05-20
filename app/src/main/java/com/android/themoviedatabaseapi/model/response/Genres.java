package com.android.themoviedatabaseapi.model.response;

import java.io.Serializable;


public class Genres implements Serializable {
    private int id;
    private String name;

    public Genres(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
