package com.firsttry.firsttryout.model;


public class RequestSpooApi {

    private String long_url;

    public RequestSpooApi(){}

    public RequestSpooApi(String long_url)
    {
        this.long_url = long_url;
    }

    public String getLong_url() {
        return long_url;
    }
    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }
}
