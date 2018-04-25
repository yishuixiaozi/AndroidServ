package com.hhit.controller;

import com.hhit.model.Job;
import com.hhit.service.JobService;
import com.hhit.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/JobServlet")
public class JobServlet {

    @Resource
    private JobService jobService;
    Job job=new Job();
    List<Job> jobList=new ArrayList<>();

    SingleObject singleObject=new SingleObject();
    ListObject listObject=new ListObject();

    /**
     * 依据客户端传递过来的参数按照每八条返回列表
     * @param request
     * @param response
     * @param pagenum
     */
    @RequestMapping("/getJobByPage")
    public void getJobByPage(HttpServletRequest request, HttpServletResponse response,int pagenum){
        System.out.println("pagenum="+pagenum);
        jobList=jobService.getJobByPage(pagenum);
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("访问成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping("/getJobByType")
    public void getJobByType(HttpServletResponse response, HttpServletRequest request){
        /*String querytype=job1.getQuerytype();*/
        Job job2=new Job();
        String querytype="kind";
        if (querytype.equals("kind")){//按多重组合查询
            System.out.println("多重组和查询");
            job2.setJobtype("校园兼职");
            job2.setPayway("日结");
           job2.setGender("all");
            jobList=jobService.getJobByKind(job2);
        }else if(querytype.equals("like")) {//按关键字查询
            jobList=jobService.getJobByLike("兵");
        }else {//默认查询，查询所有的 内容
            System.out.println("默认查询");
        }
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("访问成功");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    /**
     * 获取id获取兼职信息的详细内容
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("/getJobById")
    public void getJobById(HttpServletRequest request,HttpServletResponse response,int id){
        job=jobService.getJobById(id);
        singleObject.setObject(job);
        singleObject.setCode(StatusCode.CODE_SUCCESS);
        singleObject.setMsg("访问成功");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(singleObject));
    }


}
