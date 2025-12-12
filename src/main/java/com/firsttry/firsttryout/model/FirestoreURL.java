package com.firsttry.firsttryout.model;

public class FirestoreURL {


    private String url;
    private String password;

    public FirestoreURL(){}

    public FirestoreURL(String longUrl){
        this.url = longUrl;
        this.password = "";
    }

    public FirestoreURL(String longUrl, String password) {
        this.url = longUrl;
        this.password = "";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String longUrl) {
        this.url = longUrl;
    }
}
