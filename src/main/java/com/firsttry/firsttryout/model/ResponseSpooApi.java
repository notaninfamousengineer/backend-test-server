package com.firsttry.firsttryout.model;





public class ResponseSpooApi {


    private String alias;
    private long created_at;
    private String long_url;
    private String owner_id;
    private String private_stats;
    private String short_url;
    private String status;

    public ResponseSpooApi(){
    }


    public ResponseSpooApi(String alias, long created_at, String long_url, String owner_id, String private_stats, String short_url, String status) {
        this.alias = alias;
        this.created_at = created_at;
        this.long_url = long_url;
        this.owner_id = owner_id;
        this.private_stats = private_stats;
        this.short_url = short_url;
        this.status = status;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
