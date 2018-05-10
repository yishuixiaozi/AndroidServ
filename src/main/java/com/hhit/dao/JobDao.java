package com.hhit.dao;

import com.hhit.model.Job;
import jdk.nashorn.internal.scripts.JO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobDao {
    List<Job> getJobByPage(int pagenum);
    Job getJobById(int id);
    List<Job> getJobByLike(String queryfield);
    List<Job> getJobByKind(Job job);
    List<Job> getJobBykind(@Param("job") Job job,
                           @Param("pagenum") int pagenum);//尝试注获取组合查询
    List<Job> getJobBylike(@Param("queryfield") String queryfield,
                           @Param("pagenum") int pagenum);//尝试注解获取关键字查询

    List<Job> selectAlljob();

    void addJobBean(Job job);
}
