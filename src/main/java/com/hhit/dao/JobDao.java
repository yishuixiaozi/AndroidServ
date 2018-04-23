package com.hhit.dao;

import com.hhit.model.Job;

import java.util.List;

public interface JobDao {
    List<Job> getJobByPage(int pagenum);
    Job getJobById(int id);
}
