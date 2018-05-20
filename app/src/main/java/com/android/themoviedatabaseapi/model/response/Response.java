package com.android.themoviedatabaseapi.model.response;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {

    private ArrayList<NowPlaying> results;
    private String page;
    private String total_results;
    private MoviesDates dates;
    private String total_pages;

    public Response(ArrayList<NowPlaying> results, String page, String total_results, MoviesDates dates, String total_pages) {
        this.results = results;
        this.page = page;
        this.total_results = total_results;
        this.dates = dates;
        this.total_pages = total_pages;
    }

    public ArrayList<NowPlaying> getResults() {
        return results;
    }

    public String getPage() {
        return page;
    }

    public String getTotal_results() {
        return total_results;
    }

    public MoviesDates getDates() {
        return dates;
    }

    public String getTotal_pages() {
        return total_pages;
    }

}
