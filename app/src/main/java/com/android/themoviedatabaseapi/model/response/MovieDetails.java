package com.android.themoviedatabaseapi.model.response;

import java.io.Serializable;
import java.util.ArrayList;



public class MovieDetails implements Serializable {

    private boolean adult;
    private String backdrop_path;
    private BelongsToCollections belongs_to_collection;
    private long budget;
    private ArrayList<Genres> genres;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private ArrayList<ProductionCompanies> production_companies;
    private ArrayList<ProductionCountries> production_countries;
    private String release_date;
    private long revenue;
    private int runtime;
    private ArrayList<SpokenLanguages> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private float vote_average;
    private int vote_count;

    public MovieDetails(boolean adult, String backdrop_path, BelongsToCollections belongs_to_collection, long budget, ArrayList<Genres> genres, String homepage, int id, String imdb_id, String original_language, String original_title, String overview, double popularity, String poster_path, ArrayList<ProductionCompanies> production_companies, ArrayList<ProductionCountries> production_countries, String release_date, long revenue, int runtime, ArrayList<SpokenLanguages> spoken_languages, String status, String tagline, String title, boolean video, float vote_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.belongs_to_collection = belongs_to_collection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spoken_languages = spoken_languages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public BelongsToCollections getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public long getBudget() {
        return budget;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public ArrayList<ProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public ArrayList<ProductionCountries> getProduction_countries() {
        return production_countries;
    }

    public String getRelease_date() {
        return release_date;
    }

    public long getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public ArrayList<SpokenLanguages> getSpoken_languages() {
        return spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }
}
