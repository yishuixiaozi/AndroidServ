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
    void addJobBean(Job job);
    List<Job> queryMypost(String userid);
    void deleteByid(int id);
    List<Job> selectJobstate();
    Job selectJobdetail(int id);
    void updatenosure(Job job);
    void updateoksure(int id);
    List<Job> getJobthrough(String userid);
    List<Job> getJobnothrough(String userid);
    List<Job> getJobnoreview(String userid);
    List<Job> getJobhomelike(String queryfield,int pagenum);
    void updateJobpost(Job job);
    void updateviewtimes(int viewtimes,int id);
    List<Job> getJobremen();
}
