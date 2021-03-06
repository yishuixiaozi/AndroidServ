package com.hhit.controller;

import com.hhit.model.User;
import com.hhit.service.UserService;
import com.hhit.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/UserServlet")
public class UserServlet {
    @Resource
    private UserService userService;
    User user=new User();//专门用于存取user对象

    List<User> userList=new ArrayList<>();
    SingleObject singleObject=new SingleObject();
    ListObject listObject=new ListObject();
    /**
     * 通过用户id获取一个user对象
     * @param request
     * @param response
     */
    @RequestMapping("/getUserById")
    public void getUserById(HttpServletRequest request, HttpServletResponse response,String userid){
        System.out.println("getUserById----------"+userid);
        user=userService.getUserById(userid);
        System.out.println("-----"+user.getUsername());
        user.setPassword(" ");
        singleObject.setCode(StatusCode.CODE_SUCCESS);
        singleObject.setObject(user);
        singleObject.setMsg("访问成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(singleObject));
    }
    @RequestMapping("/getUserByUserid2")
    public void getUserById2(HttpServletRequest request, HttpServletResponse response){
        String userid=request.getParameter("userid");
        userService.getUserById(userid);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }
    @RequestMapping("/getUserByUserid")
    public void getUserByUserid(HttpServletRequest request,HttpServletResponse response){
        String userid=request.getParameter("userid");
        user=userService.getUserByUserid(userid);
        singleObject.setCode(StatusCode.CODE_SUCCESS);
        singleObject.setObject(user);
        singleObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(singleObject));
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
        singleObject.setCode(user.getUserid());
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
        System.out.println("-------------三方登录内容-----------");
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

    /**
     * 这个是服务器端图形界面登陆使用
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mlogin")
    @ResponseBody
    public Map<String,Object> mlogin(HttpServletRequest request) throws Exception{
        Map<String,Object> map=new HashMap<String, Object>();
        System.out.println("查看中文名"+request.getParameter("username"));
        System.out.println("密码"+request.getParameter("password"));
        if (request.getParameter("username")==null){
            map.put("msg","wrong");
        }else {
            user=userService.selectByusername(request.getParameter("username"));
            if (user==null){
                map.put("msg","wrong");
            }else {
                if (user.getPassword().equals(request.getParameter("password"))){
                    HttpSession session=request.getSession();
                    session.setAttribute("username",user.getUsername());//存放session的值
                    map.put("msg","success");
                }else {
                    map.put("msg","wrong");
                }
            }
        }
        return map;
    }

    /**
     * 求职者用户查询
     * @param modelMap
     * @return
     */
    @RequestMapping("/quserquery")
    public String quserquery(ModelMap modelMap){
        userList=userService.selectAllquser();
        modelMap.addAttribute("userlist",userList);
        return "quserquery";
    }

    /**
     * 招聘用户查询
     * @param modelMap
     * @return
     */
    @RequestMapping("/fuserquery")
    public String fuserquery(ModelMap modelMap){
        userList=userService.selectAllfuser();
        modelMap.addAttribute("userlist",userList);
        return "fuserquery";
    }

    @RequestMapping("/getAlluser")
    public void getAlluser(HttpServletRequest request,HttpServletResponse response){
        System.out.println("-----开始取值");
        userList=userService.getAlluser();
        System.out.println("----------"+userList.size());
        listObject.setItems(userList);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("success");
        ResponseUtils.renderJson(response,JackJsonUtils.toJson(listObject));
    }

    @RequestMapping("/quserdeleteid")
    public String userdeleteid(HttpServletRequest request){
        int id= Integer.parseInt(request.getParameter("id"));
        userService.quserdeleteid(id);
        return "redirect:quserquery";
    }

    @RequestMapping("/fuserdeleteid")
    public String fuserdeleteid(HttpServletRequest request){
        String userid=request.getParameter("userid");
        userService.fuserdeleteid(userid);
        return "redirect:fuserquery";
    }

    //求职者更新自我信息
    @RequestMapping("/updateQuserinfo")
    public void updateQuserinfo(HttpServletRequest request,HttpServletResponse response,@RequestBody User user){
        userService.updateQuserinfo(user);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }
    //招聘者gengxin
    @RequestMapping("/updateFuserinfo")
    public void updateFuserinfo(HttpServletRequest request,HttpServletResponse response,@RequestBody User user){
        userService.updateFuserinfo(user);
        ResponseUtils.renderJson(response,JackJsonUtils.toJson("success"));
    }

}
