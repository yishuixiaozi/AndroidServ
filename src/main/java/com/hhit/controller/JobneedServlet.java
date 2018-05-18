package com.hhit.controller;

import com.hhit.model.JobneedBean;
import com.hhit.service.JobneedService;
import com.hhit.utils.*;
import org.springframework.stereotype.Controller;
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
}
