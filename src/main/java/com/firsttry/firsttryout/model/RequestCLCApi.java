package com.firsttry.firsttryout.model;

public class RequestCLCApi {


    private String domain= "clc.is";;
    private String target_url;

    public RequestCLCApi(){}


    public RequestCLCApi(String target_url) {
        this.target_url = target_url;
    }

    public RequestCLCApi(String domain, String target_url) {
        this.domain = domain;
        this.target_url = target_url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }


    //    {
//        "domain": "clc.is",
//        "target_url": "https://example.com",
//        "slug": "custom", // optional
//        "expired_url": "https://expired.example.com", // optional
//        "expired_hours": 48 // optional (0 = no expiration)
//    }
}
