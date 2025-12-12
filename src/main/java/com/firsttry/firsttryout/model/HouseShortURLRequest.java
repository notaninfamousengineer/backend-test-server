package com.firsttry.firsttryout.model;

public class HouseShortURLRequest {

    private String url;
    private boolean customAISlug;

    public HouseShortURLRequest(){}

    public HouseShortURLRequest(String url, boolean customAISlug) {
        this.url = url;
        this.customAISlug = customAISlug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCustomAISlug() {
        return customAISlug;
    }

    public void setCustomAISlug(boolean customAISlug) {
        this.customAISlug = customAISlug;
    }
}
