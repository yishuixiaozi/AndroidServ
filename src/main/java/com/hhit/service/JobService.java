package com.hhit.service;

import com.hhit.model.Job;
import jdk.nashorn.internal.scripts.JO;

import java.util.List;

public interface JobService {
    List<Job> getJobByPage(int pagenum);
    Job getJobById(int id);
    List<Job> getJobByLike(String queryfield);
    List<Job> getJobByKind(Job job);
    List<Job> getJobBykind(Job job,int pagenum);
    List<Job> getJobBylike(String queryfield,int pagenum);
    List<Job> selectAlljob();
}
