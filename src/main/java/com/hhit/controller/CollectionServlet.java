package com.hhit.controller;

import com.hhit.model.CollectionBean;
import com.hhit.service.CollectionService;
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
@RequestMapping("/CollectionServlet")
public class CollectionServlet {

    @Resource
    private CollectionService collectionService;
    CollectionBean collectionBean =new CollectionBean();
    List<CollectionBean> collectionBeanList =new ArrayList<>();

    SingleObject singleObject=new SingleObject();
    ListObject listObject=new ListObject();

    @RequestMapping("/addCollection")
    public void addCollection(HttpServletResponse response, HttpServletRequest request, @RequestBody CollectionBean collectionBean1){
        System.out.println("test 图片地址测试="+ collectionBean1.getJobimageuri());
        collectionService.addCollection(collectionBean1);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("success"));
    }

    /**
     * 每个用户查询自己收藏的所有职位的时候显示
     * @param request
     * @param response
     */
    @RequestMapping("/getAllCollection")
    public void getAllCollection(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        System.out.println("userid=="+userid);
        collectionBeanList=collectionService.getAllCollection(userid);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("收藏查询成功");
        listObject.setItems(collectionBeanList);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));//数据访问
    }

    @RequestMapping("/getCollectionTag")
    public void getCollectionTag(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        int id= Integer.parseInt(request.getParameter("jobid"));
        System.out.println("------"+id);
        collectionBean=collectionService.getCollectionTag(userid,id);
        if (collectionBean==null){//如果为空
            singleObject.setMsg("no");
        }
        else {
            singleObject.setMsg("yes");
        }
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(singleObject));
    }
    @RequestMapping("/deleteCollection")
    public void deleteCollection(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        int id= Integer.parseInt(request.getParameter("jobid"));
        System.out.println("----id测试"+id);
        collectionService.deleteCollection(userid,id);
        singleObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(singleObject));

    }
}
