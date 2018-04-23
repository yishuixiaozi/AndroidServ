package com.hhit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Test")
public class Test {
    //ceshi
    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("userid");
        System.out.println("看看是否成功了你大爷的" +username);
    }
}
