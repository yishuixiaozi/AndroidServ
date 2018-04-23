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
}
