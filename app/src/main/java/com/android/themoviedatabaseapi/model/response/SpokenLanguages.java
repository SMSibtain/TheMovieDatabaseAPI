package com.android.themoviedatabaseapi.model.response;

import java.io.Serializable;


class SpokenLanguages implements Serializable {
    private String iso_639_1;
    private String name;

    public SpokenLanguages(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getName() {
        return name;
    }

}
