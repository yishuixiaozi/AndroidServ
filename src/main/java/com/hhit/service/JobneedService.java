package com.hhit.service;

import com.hhit.model.JobneedBean;

import java.util.List;

public interface JobneedService {

    List<JobneedBean> getJobneedByPage(int pagenum);
}
