package com.android.themoviedatabaseapi.model.response;

import java.io.Serializable;


public class ProductionCountries implements Serializable {
   private String iso_3166_1;
   private String name;

    public ProductionCountries(String iso_3166_1, String name) {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }


    public String getName() {
        return name;
    }

}
