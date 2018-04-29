package com.hhit.controller;

import com.hhit.model.CollectionBean;
import com.hhit.service.CollectionService;
import com.hhit.utils.JackJsonUtils;
import com.hhit.utils.ListObject;
import com.hhit.utils.ResponseUtils;
import com.hhit.utils.SingleObject;
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
}
