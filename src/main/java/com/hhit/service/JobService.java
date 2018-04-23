package com.hhit.service;

import com.hhit.model.Job;

import java.util.List;

public interface JobService {
    List<Job> getJobByPage(int pagenum);
    Job getJobById(int id);
}
