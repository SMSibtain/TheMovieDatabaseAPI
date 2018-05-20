package com.android.themoviedatabaseapi.model.response;

import java.io.Serializable;


public class BelongsToCollections implements Serializable {
    private int id;
    private String name;
    private String poster_path;
    private String backdrop_path;

    public BelongsToCollections(int id, String name, String poster_path, String backdrop_path) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

}
