package com.hhit.model;

import java.io.Serializable;

public class JobneedBean implements Serializable {
    private int jobneedid;
    private String jobneedtitle;
    private String jobneedcontent;
    private String userid;
    private String username;
    private String phonum;
    private String jobneedtype;
    private String worktime;
    private String bftime;
    private String myadvantage;
    private String jobimageuri;

    public int getJobneedid() {
        return jobneedid;
    }

    public void setJobneedid(int jobneedid) {
        this.jobneedid = jobneedid;
    }

    public String getJobneedtitle() {
        return jobneedtitle;
    }

    public void setJobneedtitle(String jobneedtitle) {
        this.jobneedtitle = jobneedtitle;
    }

    public String getJobneedcontent() {
        return jobneedcontent;
    }

    public void setJobneedcontent(String jobneedcontent) {
        this.jobneedcontent = jobneedcontent;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonum() {
        return phonum;
    }

    public void setPhonum(String phonum) {
        this.phonum = phonum;
    }

    public String getJobneedtype() {
        return jobneedtype;
    }

    public void setJobneedtype(String jobneedtype) {
        this.jobneedtype = jobneedtype;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getBftime() {
        return bftime;
    }

    public void setBftime(String bftime) {
        this.bftime = bftime;
    }

    public String getMyadvantage() {
        return myadvantage;
    }

    public void setMyadvantage(String myadvantage) {
        this.myadvantage = myadvantage;
    }

    public String getJobimageuri() {
        return jobimageuri;
    }

    public void setJobimageuri(String jobimageuri) {
        this.jobimageuri = jobimageuri;
    }
}
