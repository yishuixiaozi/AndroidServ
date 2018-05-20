package com.hhit.service;

import com.hhit.model.JobneedBean;

import java.util.List;

public interface JobneedService {

    List<JobneedBean> getJobneedByPage(int pagenum);

    List<JobneedBean> getJoblike(String queryfield,int pagenum);
}
