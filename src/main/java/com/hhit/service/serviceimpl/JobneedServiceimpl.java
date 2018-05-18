package com.hhit.service.serviceimpl;

import com.hhit.dao.JobneedDao;
import com.hhit.model.JobneedBean;
import com.hhit.service.JobneedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value ="jobneedService")
public class JobneedServiceimpl implements JobneedService{

    @Resource
    JobneedDao jobneedDao;
    @Override
    public List<JobneedBean> getJobneedByPage(int pagenum) {
        return jobneedDao.getJobneedByPage(pagenum);
    }
}
