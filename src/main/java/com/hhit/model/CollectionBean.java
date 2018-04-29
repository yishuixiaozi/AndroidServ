package com.hhit.model;

import java.io.Serializable;

public class CollectionBean implements Serializable{
    private int collectionid;
    private int jobid;
    private String userid;
    private String time;

    private String title;
    private String paymoney;
    private String payway;
    private String worktime;
    private String jobimageuri;

    public int getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(int collectionid) {
        this.collectionid = collectionid;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getJobimageuri() {
        return jobimageuri;
    }

    public void setJobimageuri(String jobimageuri) {
        this.jobimageuri = jobimageuri;
    }
}
