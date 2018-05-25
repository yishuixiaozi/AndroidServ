package com.hhit.dao;

import com.hhit.model.JobneedBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobneedDao {
    List<JobneedBean> getJobneedByPage(int pagenum);

    List<JobneedBean> getJoblike(@Param("queryfield") String queryfield,
                                 @Param("pagenum") int pagenum);
    void postJobneed(JobneedBean jobneedBean);
    List<JobneedBean> querymypost(int userid);
    void deletemypost(int jobneedid);
}
