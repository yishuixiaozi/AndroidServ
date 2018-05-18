package com.hhit.dao;

import com.hhit.model.JobneedBean;

import java.util.List;

public interface JobneedDao {
    List<JobneedBean> getJobneedByPage(int pagenum);
}
