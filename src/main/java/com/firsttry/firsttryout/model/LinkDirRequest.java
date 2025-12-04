package com.firsttry.firsttryout.model;

import java.util.List;

public class LinkDirRequest {

    private String linkDirName;
    private String linkDirDesc;
    private List<String> linksList;
    private String userId;


    public LinkDirRequest() {}

    public LinkDirRequest(String LinkDirName, String LinkDirDesc, List<String> linksList, String userID) {
        this.linkDirName = LinkDirName;
        this.linkDirDesc = LinkDirDesc;
        this.linksList = linksList;
        this.userId = userID;
    }

    public String getLinkDirName() {
        return linkDirName;
    }

    public void setLinkDirName(String linkDirName) {
        this.linkDirName = linkDirName;
    }

    public String getLinkDirDesc() {
        return linkDirDesc;
    }

    public void setLinkDirDesc(String linkDirDesc) {
        this.linkDirDesc = linkDirDesc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getLinksList() {
        return linksList;
    }

    public void setLinksList(List<String> linksList) {
        this.linksList = linksList;
    }
}
