package com.firsttry.firsttryout.model;


import java.security.SecureRandom;

public class Urls {

    private int urlid;
    private String longUrl;
    private String shortUrl;



//    public Urls(String longUrl) {
//        this.longUrl = longUrl;
//    }

    public Urls(int urlid, String longUrl, String shortUrl) {
        this.urlid = urlid;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public int getUrlid() {
        return urlid;
    }

    public void setUrlid(int urlid) {
        this.urlid = urlid;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
