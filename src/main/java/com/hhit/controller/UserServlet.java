package com.hhit.controller;

import com.hhit.model.User;
import com.hhit.service.UserService;
import com.hhit.utils.JackJsonUtils;
import com.hhit.utils.ResponseUtils;
import com.hhit.utils.SingleObject;
import com.hhit.utils.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/UserServlet")
public class UserServlet {
    @Resource
    private UserService userService;
    User user=new User();//专门用于存取user对象
    SingleObject singleObject=new SingleObject();
    /**
     * 通过用户id获取一个user对象
     * @param request
     * @param response
     */
    @RequestMapping("/getUserById")
    public void getUserById(HttpServletRequest request, HttpServletResponse response,int userid){
        System.out.println("getUserById----------"+userid);
        user=userService.getUserById(userid);
        user.setPassword(" ");
        singleObject.setCode(StatusCode.CODE_SUCCESS);
        singleObject.setObject(user);
        singleObject.setMsg("访问成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(singleObject));
    }
    /**
     * 依据求职者用户名，密码登陆处理结果
     * @param request
     * @param response
     */
    @RequestMapping("/getUser")
    public void getUser(HttpServletRequest request, HttpServletResponse response,User user1,String usertype){
        System.out.println("test username=="+user1.getUsername());
        System.out.println("test password=="+user1.getPassword());
        System.out.println("usertype==="+usertype);
        if (usertype.equals("Employee")){
            user=userService.getUser(user1);
        }else {
            user=userService.getUser2(user1);
        }
        //singleObject.setObject(user);
        singleObject.setCode(StatusCode.CODE_SUCCESS);
        if (user==null){
            System.out.println("数据库没有这个用户");
            singleObject.setMsg("failure");
        }else {
            System.out.println("数据库查到了这个用户了");
            singleObject.setMsg("success");
        }
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(singleObject));
    }

    /**
     * 招聘者用户登录的放方法
     * @param request
     * @param response
     * @param user1
     */
    @RequestMapping("/getUser2")
    public void getUser2(HttpServletRequest request,HttpServletResponse response,User user1){
        user=userService.getUser2(user1);
        singleObject.setCode(StatusCode.CODE_SUCCESS);
        if (user==null){
            singleObject.setMsg("failure");
        }else{
            singleObject.setMsg("success");
        }
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(singleObject));
    }
    /**
     * QQ用户的三方登录更新或者删除操作
     * @param request
     * @param response
     * @param user1
     */
    @RequestMapping("/UUserByUserid")
    public void UIUserById(HttpServletRequest request,HttpServletResponse response,@RequestBody User user1){
        System.out.println("--------测试user里边的是否有值的"+user1.getUserid());
        System.out.println("-------测试用户类型-----"+user1.getUsertype());
        if (user1.getUsertype().equals("Employee")){//这是求职者
            System.out.println("-------求职者");
            user=userService.getUserByUserid(user1.getUserid());//判断返回值
            if(user==null){//为空执行添加
                userService.insertUser(user1);
            }else {//不为空修改
                userService.updateUser(user1);
            }
        }else {//这是招聘者
            System.out.println("------招聘者");
            user=userService.getUser2ByUserid(user1.getUserid());
            if (user==null){
                System.out.println("招聘者的用户表为空,执行插入");
                userService.insertUser2(user1);
            }else {
                System.out.println("招聘用户表存在，执行更新");
                userService.updateUser2(user1);
            }
        }
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(user1.getUserid()));
    }
    /**
     * 招聘者的三方登录使用数据存储
     * @param request
     * @param response
     * @param user1
     */
    @RequestMapping("/UUser2ByUserid")
    public void UIUser2ById(HttpServletRequest request,HttpServletResponse response,@RequestBody User user1){
        user=userService.getUser2ByUserid(user1.getUserid());
        if (user==null){
            userService.insertUser2(user1);
        }else {
            userService.updateUser2(user1);
        }
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(user1.getUserid()));
    }

    /**
     * 用户注册界面的内容
     * @param request
     * @param response
     * @param user1
     */
    @RequestMapping("/UserRegister")
    public void UserRegister(HttpServletRequest request,HttpServletResponse response,@RequestBody User user1){
        String usertype=user1.getUsertype();
        System.out.println("user1-------"+user1.getUserid()+"用户名"+user1.getUsername()+"密码"+user1.getPassword());
        if (usertype.equals("Employee")){
            userService.insertUser(user1);
        }else if (usertype.equals("Employer")){
            userService.insertUser2(user1);
        }else {
            System.out.println("客户端传递过来的参数出错");
        }
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }
    @RequestMapping("/test")
    public String test(){
        user=userService.selectByusername("zxr");
        System.out.println("user对象获取值的测试="+user.getNickname());
        return "login";
    }

}
