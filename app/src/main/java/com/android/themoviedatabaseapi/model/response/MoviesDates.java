package com.android.themoviedatabaseapi.model.response;

import java.io.Serializable;

public class MoviesDates implements Serializable {

    private String maximum;
    private String minimum;

    public MoviesDates(String maximum, String minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public String getMinimum() {
        return minimum;
    }

}
