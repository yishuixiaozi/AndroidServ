package com.hhit.controller;

import com.hhit.model.Job;
import com.hhit.service.JobService;
import com.hhit.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public void getJobByType(HttpServletResponse response, HttpServletRequest request,@RequestBody Job job1,int pagenum){
        /*String querytype=job1.getQuerytype();*/
        //Job job2=new Job();
        System.out.println("querytype="+job1.getQuerytype());
        System.out.println("page="+pagenum);
        if (job1.getQuerytype().equals("kind")){//按多重组合查询
            System.out.println("多重组和查询");
            System.out.println("jobtype"+job1.getJobtype());
            System.out.println("payway"+job1.getPayway());
            System.out.println("gender"+job1.getGender());
            jobList=jobService.getJobBykind(job1,pagenum);
        }else if(job1.getQuerytype().equals("like")) {//按关键字查询
            jobList=jobService.getJobBylike(job1.getQueryfield(),pagenum);
        }else {//默认查询，查询所有的 内容
            System.out.println("默认查询");
            jobList=jobService.getJobByPage(pagenum);
        }
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("访问成功");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    /**
     * 测试内容
     * @param request
     * @param response
     */
    @RequestMapping("/getJobTest")
    public void getJobByTypeTest(HttpServletRequest request,HttpServletResponse response){
        Job job1=new Job();
        job1.setJobtype("校园兼职");
        job1.setPayway("结算时间");
        job1.setGender("男");
        jobList=jobService.getJobBykind(job1,0);
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
    /**
     * 服务器端获取兼职信息查询
     * @param modelMap
     */
    @RequestMapping("/jobquery")
    public String jobquery(ModelMap modelMap){
        jobList=jobService.selectAlljob();
        modelMap.addAttribute("joblist",jobList);
        return "jobquery";
    }


}
