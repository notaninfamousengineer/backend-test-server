package com.firsttry.firsttryout.model;

public class ResponseCLCApi {



    private RequestCLCApi input;
    private String slug;
    private String url;
    private boolean is_generated;

    public ResponseCLCApi(){
    }

    public ResponseCLCApi(RequestCLCApi input, String slug, String url, boolean is_generated) {
        this.input = input;
        this.slug = slug;
        this.url = url;
        this.is_generated = is_generated;
    }

    public RequestCLCApi getInput() {
        return input;
    }

    public void setInput(RequestCLCApi input) {
        this.input = input;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isIs_generated() {
        return is_generated;
    }

    public void setIs_generated(boolean is_generated) {
        this.is_generated = is_generated;
    }

    //    {
//        "input": { ... },
//        "slug": "custom",
//        "url": "https://clc.is/custom",
//        "is_generated": false
//    }
}
