package com.android.themoviedatabaseapi.model.request;

public class Request {
    private String api_key;
    private String language;
    private String page;
    private String region;

    public Request(String api_key, String language, String page, String region) {
        this.api_key = api_key;
        this.language = language;
        this.page = page;
        this.region = region;
    }

    public String getApi_key() {
        return api_key;
    }

    public String getLanguage() {
        return language;
    }

    public String getPage() {
        return page;
    }

    public String getRegion() {
        return region;
    }
}
