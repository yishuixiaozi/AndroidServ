package com.hhit.controller;

import com.hhit.model.JobneedBean;
import com.hhit.service.JobneedService;
import com.hhit.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/JobneedServlet")
public class JobneedServlet {
    @Resource
    JobneedService jobneedService;

    JobneedBean jobneedBean=new JobneedBean();
    List<JobneedBean> jobneedBeanList=new ArrayList<>();

    SingleObject singleObject=new SingleObject();
    ListObject listObject=new ListObject();

    @RequestMapping("/getJobneedByPage")
    public void getJobneedByPage(HttpServletResponse response, HttpServletRequest request){
        int pagenum= Integer.parseInt(request.getParameter("pagenum"));
        jobneedBeanList=jobneedService.getJobneedByPage(pagenum);
        listObject.setItems(jobneedBeanList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("数据获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping("/getJoblike")
    public void getJoblike(HttpServletResponse response, HttpServletRequest request){
        int pagenum= Integer.parseInt(request.getParameter("pagenum"));
        String queryfield=request.getParameter("queryfield");
        System.out.println("====="+pagenum+queryfield);
        jobneedBeanList=jobneedService.getJoblike(queryfield,pagenum);
        listObject.setItems(jobneedBeanList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    /**
     * 求职者发布自己的职位需求
     * @param request
     * @param response
     * @param jobneedBean
     */
    @RequestMapping("/postJobneed")
    public void postJobneed(HttpServletRequest request, HttpServletResponse response, @RequestBody JobneedBean jobneedBean){
        System.out.println("----test----"+jobneedBean.getBftime());
        jobneedService.postJobneed(jobneedBean);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }

    /**
     * 求职者查询我的求职发布信息
     * @param request
     * @param response
     */
    @RequestMapping("/querymypost")
    public void querymypost(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        jobneedBeanList=jobneedService.querymypost(userid);
        listObject.setItems(jobneedBeanList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    /**
     * 求职者删除自我的求职发布信息
     * @param request
     * @param response
     */
    @RequestMapping("/deletemypost")
    public void deletemypost(HttpServletRequest request,HttpServletResponse response){
        int jobneedid= Integer.parseInt(request.getParameter("jobneedid"));
        jobneedService.deletemypost(jobneedid);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }

    //获取我的求职发布某条详细信息
    @RequestMapping("/getJobneeddetail")
    public void getJobneeddetail(HttpServletRequest request,HttpServletResponse response) {
        int jobneedid= Integer.parseInt(request.getParameter("jobneedid"));
        jobneedBean=jobneedService.getJobneeddetail(jobneedid);
        singleObject.setObject(jobneedBean);
        singleObject.setCode(StatusCode.CODE_SUCCESS);
        singleObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(singleObject));
    }

    //获取我的某条求职发布信息
    @RequestMapping("/updateJobneed")
    public void updateJobneed(HttpServletRequest request,HttpServletResponse response,@RequestBody JobneedBean jobneedBean){
        System.out.println("------太长的worktime---"+jobneedBean.getWorktime());
        jobneedService.updateJobneed(jobneedBean);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }
}
