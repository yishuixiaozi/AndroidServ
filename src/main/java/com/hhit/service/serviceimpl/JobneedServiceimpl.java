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

    @Override
    public List<JobneedBean> getJoblike(String queryfield, int pagenum) {
        return jobneedDao.getJoblike(queryfield,pagenum);
    }

    @Override
    public void postJobneed(JobneedBean jobneedBean) {
        jobneedDao.postJobneed(jobneedBean);
    }

    @Override
    public List<JobneedBean> querymypost(String userid) {
        return jobneedDao.querymypost(userid);
    }

    @Override
    public void deletemypost(int jobneedid) {
        jobneedDao.deletemypost(jobneedid);
    }

    @Override
    public JobneedBean getJobneeddetail(int jobneedid) {
        return jobneedDao.getJobneeddetail(jobneedid);
    }

    @Override
    public void updateJobneed(JobneedBean jobneedBean) {
        jobneedDao.updateJobneed(jobneedBean);
    }

    @Override
    public List<JobneedBean> jobneedquery() {
        return jobneedDao.jobneedquery();
    }
}
