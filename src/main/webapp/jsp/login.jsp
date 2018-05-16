<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>人事管理系统new</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
    <%--<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>--%>

    <script type="text/javascript">
        function loginpass(){
            /*alert("触发事件成功");*/
            $.ajax({
                data: {username:$("#username").val(), password:$("#password").val()},
                type:"post",
                dataType:'json',
                url:"UserServlet/mlogin",
                error:function (data) {
                    alert("出现些问题");
                },
                success:function(data){
                    /*alert(data["msg"]);*///返回的data是一个map,所以data["msg"]是对象的值
                    var msg1=data["msg"];
                    /*alert(msg1);*/
                    if(msg1=="wrong")
                    {
                        /*alert(msg1);*/
                        alert("用户名或密码错误");
                        $("#loginform1").resetForm();
                    }
                    else{
                        top.location.href = "jsp/main.jsp"
                    }
                    /*window.location.href = "view/login.jsp";这个跳转后还是子页面*/
                    /*top.loaction.href的跳转是跳出子页面成为最外层页面*/
                }
            });
        }
    </script>
</head>

<body>
<div class="header">
    <div class="inner_c">
        <div class="header_left">Design By Zhou</div>
        <div class="header_right">
            <div class="hri_left">
                <ul>
                    <li>登录&nbsp;&nbsp;&nbsp;&nbsp;|</li>
                    <li>注册&nbsp;&nbsp;&nbsp;&nbsp;|</li>
                    <li>公司合作&nbsp;&nbsp;|</li>
                </ul>
            </div>
            <div class="hri_right">咨询热线：</div>
        </div>
    </div>
</div>
<div class="body_top">
    <div class="inner_body">
        <p><img src="images/flower.jpg"/></p>
        <p class="p_id1"></p>
    </div>
</div>
<div class="body_center">
    <div class="inner_body2">
        <div class="bc_left">
            <img src="images/jiaju1.jpg">
        </div>
        <div class="bc_right">
            <p></p>
            <form  method="post" id="loginform1">
                <input type="text" class="name_input" id="username" name="username" for="reservation" placeholder="注册邮箱/用户名">
                <input type="password" class="pass_input" id="password" name="password" for="reservation" placeholder="密码">
                <input type="button" class="p_login" onclick="loginpass()" value="立即登录"/>
                <%--<p class="p_login">立即登录</p>--%>
                <a href="#">免费注册</a>
            </form>
        </div>
    </div>
</div>
<div class="bottom">
    <div class="inner_bottom">
        <div class="bottom1">
            <p class="p_l">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Copyright &copy;
                2004-2014&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;XXXXXX（Decorating &nbsp;&nbsp;Houses）
                &nbsp;&nbsp;&nbsp;&nbsp;ICPXX
                备XXXXXXXX号 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                增值电信业务经营许可证：&nbsp;&nbsp;XXXXXXXXXXX</p>
            <p class="p_r"><img src="images/govIcon.gif" alt=""></p>
            <!--<p>免责声明：本网站设计用于理论学习</p>-->
        </div>
    </div>
</div>
</body>
</html>