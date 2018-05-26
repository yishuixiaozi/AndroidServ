package com.hhit.controller;

import com.hhit.model.JobneedBean;
import com.hhit.service.JobneedService;
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
}
