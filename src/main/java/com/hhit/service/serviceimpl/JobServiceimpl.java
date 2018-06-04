package com.hhit.service.serviceimpl;

import com.hhit.dao.JobDao;
import com.hhit.model.Job;
import com.hhit.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value ="jobService")
public class JobServiceimpl implements JobService{

    @Resource
    JobDao jobDao;
    @Override
    public List<Job> getJobByPage(int pagenum) {
        return jobDao.getJobByPage(pagenum);
    }

    @Override
    public Job getJobById(int id) {
        return jobDao.getJobById(id);
    }

    @Override
    public List<Job> getJobByLike(String queryfield) {
        return jobDao.getJobByLike(queryfield);
    }

    @Override
    public List<Job> getJobByKind(Job job) {
        return jobDao.getJobByKind(job);
    }

    @Override
    public List<Job> getJobBykind(Job job, int pagenum) {
        return jobDao.getJobBykind(job,pagenum);
    }

    @Override
    public List<Job> getJobBylike(String queryfield, int pagenum) {
        return jobDao.getJobBylike(queryfield,pagenum);
    }

    @Override
    public List<Job> selectAlljob() {
        return jobDao.selectAlljob();
    }

    @Override
    public void addJobBean(Job job) {
        jobDao.addJobBean(job);
    }

    @Override
    public List<Job> queryMypost(String userid) {
        return jobDao.queryMypost(userid);
    }

    @Override
    public void deleteByid(int id) {
        jobDao.deleteByid(id);
    }

    @Override
    public List<Job> selectJobstate() {
        return jobDao.selectJobstate();
    }

    @Override
    public Job selectJobdetail(int id) {
        return jobDao.selectJobdetail(id);
    }

    @Override
    public void updatenosure(Job job) {
        jobDao.updatenosure(job);
    }

    @Override
    public void updateoksure(int id) {
        jobDao.updateoksure(id);
    }

    @Override
    public List<Job> getJobthrough(String userid) {
        return jobDao.getJobthrough(userid);
    }

    @Override
    public List<Job> getJobnothrough(String userid) {
        return jobDao.getJobnothrough(userid);
    }

    @Override
    public List<Job> getJobnoreview(String userid) {
        return jobDao.getJobnoreview(userid);
    }

    @Override
    public List<Job> getJobhomelike(String queryfield, int pagenum) {
        return jobDao.getJobhomelike(queryfield,pagenum);
    }

    @Override
    public void updateJobpost(Job job) {
        jobDao.updateJobpost(job);
    }

    @Override
    public void updateviewtimes(int viewtimes, int id) {
        jobDao.updateviewtimes(viewtimes,id);
    }

    @Override
    public List<Job> getJobremen() {
        return jobDao.getJobremen();
    }


}
