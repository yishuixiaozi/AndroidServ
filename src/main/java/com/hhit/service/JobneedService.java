package com.hhit.service;

import com.hhit.model.JobneedBean;

import java.util.List;

public interface JobneedService {

    List<JobneedBean> getJobneedByPage(int pagenum);

    List<JobneedBean> getJoblike(String queryfield,int pagenum);
    void postJobneed(JobneedBean jobneedBean);
    List<JobneedBean> querymypost(String userid);
    void deletemypost(int jobneedid);
    JobneedBean getJobneeddetail(int jobneedid);
    void updateJobneed(JobneedBean jobneedBean);
    List<JobneedBean> jobneedquery();
}
