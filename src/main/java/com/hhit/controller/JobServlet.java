package com.hhit.controller;

import com.hhit.model.Job;
import com.hhit.model.JobtypeBean;
import com.hhit.service.JobService;
import com.hhit.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/JobServlet")
public class JobServlet {

    @Resource
    private JobService jobService;
    Job job=new Job();
    List<Job> jobList=new ArrayList<>();

    SingleObject singleObject=new SingleObject();
    ListObject listObject=new ListObject();

    String typeEnglish;
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
    /*
    * 客户端招聘者用户发布招聘岗位所用
    * 审核状态未审核是数据库默认的值的内容
    * */
    @RequestMapping("/addJobBean")
    public void addJobBean(HttpServletResponse response, HttpServletRequest request,@RequestBody Job job1) throws Exception {
        System.out.println("服务器职位添加");
        IpmessageUtils ipmessageUtils = new IpmessageUtils();
        typeEnglish = JobtypeBean.getTypeEnglish(job1.getJobtype());
        //拼接一个图片的url地址出来利用不同的职业类型形成的对应的图片的url
        String jobimageuri = ipmessageUtils.getIphostAddress()+typeEnglish+".png";
        System.out.println("测试jobimageuri的内容"+jobimageuri);
        job1.setJobimageuri(jobimageuri);
        jobService.addJobBean(job1);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
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
        //这个地方进行viewtimes的+1操作
        System.out.println("--------------"+id);
        job=jobService.getJobById(id);
        int viewtimes=job.getViewtimes()+1;
        jobService.updateviewtimes(viewtimes,id);//更新次数
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

    /**
     * 服务器端：兼职审核信息查询没有审核的信息
     * @param modelMap
     * @return
     */
    @RequestMapping("/jobstatequery")
    public String jobstatequery(ModelMap modelMap){
        jobList=jobService.selectJobstate();
        modelMap.addAttribute("joblist",jobList);
        return "jobstatequery";
    }

    /**
     * 查询某个没有审核的兼职的详细信息
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping("/jobstatedetail")
    public String jobstatesure(HttpServletRequest request,ModelMap modelMap){
        int id= Integer.parseInt(request.getParameter("id"));
        System.out.println("获得的兼职id是----"+id);
        job=jobService.selectJobdetail(id);
        modelMap.addAttribute("job",job);
        return "jobstatedetail";
    }
    /**
     * 依据某个用户userid来获取该用户发布的兼职信息
     * @param request
     * @param response
     */
    @RequestMapping("/queryMypost")
    public void queryMypost(HttpServletRequest request,HttpServletResponse response){
        System.out.println("获取我的发布兼职信息");
        String userid=request.getParameter("userid");
        jobList=jobService.queryMypost(userid);
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取用户发布成功");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    @RequestMapping("/joboksure")
    @ResponseBody
    public Map<String,Object> joboksure(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<String, Object>();
        int jobid= Integer.parseInt(request.getParameter("jobid"));
        jobService.updateoksure(jobid);
        map.put("msg","success");
        return map;
    }

    /**
     * 这里是审核不通过执行方法
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/jobnosure")
    @ResponseBody
    public Map<String,Object> jobnosure(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<String, Object>();
        String sureresult=request.getParameter("sureresult");
        int jobid= Integer.parseInt(request.getParameter("jobid"));
        System.out.println("结果测试-----------"+sureresult+jobid);
        job.setId(jobid);
        job.setJobremark(sureresult);
        jobService.updatenosure(job);
        map.put("msg","success");
        return map;
    }
    /**
     * 依据兼职信息id来删除某个用户的发布的兼职信息
     * @param request
     * @param response
     */
    @RequestMapping("/deleteByid")
    public void deleteByid(HttpServletRequest request,HttpServletResponse response){
        int id= Integer.parseInt(request.getParameter("jobid"));
        jobService.deleteByid(id);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }

    @RequestMapping("/getJobthrough")
    public void getJobthrough(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        jobList=jobService.getJobthrough(userid);
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("审核通过获取成功");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    @RequestMapping("/getJobnothrough")
    public void getJobnothrough(HttpServletResponse response,HttpServletRequest request){
        String userid=request.getParameter("userid");
        jobList=jobService.getJobnothrough(userid);
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("未通过");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    @RequestMapping("/getJobnoreview")
    public void getJobnoreview(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        jobList=jobService.getJobnoreview(userid);
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("未审核");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    @RequestMapping("/getJobhomelike")
    public void getJobhomelike(HttpServletRequest request,HttpServletResponse response){
        String queryfield=request.getParameter("queryfield");
        int pagenum= Integer.parseInt(request.getParameter("pagenum"));
        jobList=jobService.getJobhomelike(queryfield,pagenum);
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    @RequestMapping("/updateJobpost")
    public void updateJobpost(HttpServletRequest request,HttpServletResponse response,@RequestBody Job job){
        System.out.println("------服务器执行更新");
        jobService.updateJobpost(job);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }

    /**
     * 获取热门职位内容
     * @param request
     * @param response
     */
    @RequestMapping("/getJobremen")
    public void getJobremen(HttpServletRequest request,HttpServletResponse response){
        jobList=jobService.getJobremen();
        listObject.setItems(jobList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }
    //服务器端删除
    @RequestMapping("/jobdeleteid")
    public String jobdeleteid(HttpServletRequest request){
        int id= Integer.parseInt(request.getParameter("id"));
        jobService.deleteByid(id);
        return "redirect:jobquery";
    }


    @RequestMapping("/test")
    public void test(HttpServletRequest request) throws Exception {
        IpmessageUtils ipmessageUtils = new IpmessageUtils();
        System.out.println("本地的电脑IP 是 "+ipmessageUtils.getIphostAddress()+ipmessageUtils.getIphostName());
    }
}
