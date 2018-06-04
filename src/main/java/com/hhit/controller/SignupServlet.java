package com.hhit.controller;

import com.hhit.model.SignupBean;
import com.hhit.service.SignupService;
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
@RequestMapping("/SignupServlet")
public class SignupServlet {
    @Resource
    private SignupService signupService;
    SignupBean signupBean=new SignupBean();
    List<SignupBean> signupBeanList=new ArrayList<>();
    SingleObject singleObject=new SingleObject();
    ListObject listObject=new ListObject();
    /**
     * 报名信息处理
     * @param response
     * @param request
     * @param signupBean1
     */
    @RequestMapping("/addSignup")
    public void addSignup(HttpServletResponse response, HttpServletRequest request, @RequestBody SignupBean signupBean1){
        System.out.println("test");
        signupService.addSignup(signupBean1);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("success"));
    }

    /**
     * 查询是否已经报名的方法内容
     * @param request
     * @param response
     */
    @RequestMapping("/searchSignup")
    public void searchSignup(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        int id= Integer.parseInt(request.getParameter("jobid"));
        System.out.println("------"+id);
        signupBean=signupService.searchSignup(userid,id);
        if (signupBean==null){
            singleObject.setMsg("no");
        }else {
            singleObject.setMsg("yes");
        }
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(singleObject));
    }

    @RequestMapping("/getAllSignup")
    public void getAllSignup(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        signupBeanList=signupService.getAllSignup(userid);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("访问成功");
        listObject.setItems(signupBeanList);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }
    @RequestMapping("/queryMypost")
    public void getMypost(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");

    }
    @RequestMapping("/deleteSignup")
    public void deleteSignup(HttpServletRequest request,HttpServletResponse response){
        int signupid= Integer.parseInt(request.getParameter("signupid"));
        signupService.deleteSignup(signupid);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }
    @RequestMapping("/signupquery")
    public String signupquery(ModelMap modelMap){
        signupBeanList=signupService.signupquery();
        modelMap.addAttribute("signuplist",signupBeanList);
        return "signupquery";
    }

    /**
     * 某用户某兼职的报名单
     * @param request
     * @param response
     */
    @RequestMapping("/getMypostsign")
    public void getMypostsign(HttpServletRequest request,HttpServletResponse response){
        int jobid= Integer.parseInt(request.getParameter("jobid"));
        signupBeanList=signupService.getMypostsign(jobid);
        listObject.setItems(signupBeanList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));

    }

    @RequestMapping("/signupdeleteid")
    public String signupdeleteid(HttpServletRequest request){
        int id= Integer.parseInt(request.getParameter("id"));
        signupService.deleteSignup(id);
        return "redirect:signupquery";
    }
}
