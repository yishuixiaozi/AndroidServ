<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>大学生兼职系统主页</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <%--加载的样式--%>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="css/matrix-style.css" />
    <link rel="stylesheet" href="css/matrix-media.css" />
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>

<body>
<!--Header-part-->
<div id="header">
    <h1><a href="#">大学生兼职管理系统</a></h1>
</div>
<!--close-Header-part-->
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li  class="dropdown" id="profile-messages" >
            <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                <i class="icon icon-user"></i>&nbsp;
                <span class="text">欢迎你${sessionScope.username}
               </span>&nbsp;
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="#"><i class="icon-user"></i> 个人资料</a></li>
                <li class="divider"></li>
                <li><a href="#"><i class="icon-check"></i> 我的任务</a></li>
                <li class="divider"></li>
                <li><a href="index.jsp"><i class="icon-key"></i> 退出系统</a></li>
            </ul>
        </li>
        <li class="dropdown" id="menu-messages">
        </li>
        <li class=""><a title="" href="#"><i class="icon icon-share-alt"></i> <span class="text">&nbsp;退出系统</span></a></li>
    </ul>
</div>
<div id="sidebar" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
    <ul>
        <li class="submenu active">
            <a class="menu_a" link="jsp/selfmessage.jsp"><i class="icon icon-home"></i> <span>控制面板</span></a>
        </li>
        <li class="submenu">
            <a href="#">
                <i class="icon icon-table"></i>
                <span>求职用户管理</span>
            </a>
            <ul>
                <li><a class="menu_a" id="yonghu"  link="UserServlet/quserquery"><i class="icon icon-caret-right"></i>求职用户查询</a></li>
            </ul>
        </li>
        <li class="submenu">
            <a href="#">
                <i class="icon icon-th"></i>
                <span>招聘用户管理</span>
            </a>
            <ul>
                <li><a class="menu_a" link="UserServlet/fuserquery"><i class="icon icon-caret-right"></i>招聘用户查询</a></li>
            </ul>
        </li>
        <li class="submenu">
            <a href="#">
                <i class="icon icon-stop"></i>
                <span>兼职信息管理</span>
            </a>
            <ul>
                <li><a class="menu_a" link="JobServlet/jobquery"><i class="icon icon-caret-right"></i>招聘信息查询</a></li>
                <li><a class="menu_a" link="JobServlet/jobstatequery"><i class="icon icon-caret-right"></i>兼职信息审核</a></li>
                <li><a class="menu_a" link="JobneedServlet/jobneedquery"><i class="icon icon-caret-right"></i>求职信息查询</a></li>
            </ul>
        </li>
        <li class="submenu">
            <a href="#">
                <i class="icon icon-stop"></i>
                <span>报名信息管理</span>
            </a>
            <ul>
                <li><a class="menu_a" link="SignupServlet/signupquery"><i class="icon icon-caret-right"></i>报名信息查询</a></li>
            </ul>
        </li>
        <li class="submenu">
            <a href="#">
                <i class="icon icon-th"></i>
                <span>收藏信息管理</span>
            </a>
            <ul>
                <li><a class="menu_a" link="CollectionServlet/collectionquery"><i class="icon icon-caret-right"></i>收藏信息查询</a></li>
            </ul>
        </li>
        <li class="submenu">
            <a href="#">
                <i class="icon icon-table"></i>
                <span>数据信息管理</span>
            </a>
            <ul>
                <li><a class="menu_a" id="yo"  link="view/uploadForm1.jsp"><i class="icon icon-caret-right"></i>数据库备份</a></li>
                <li><a class="menu_a" id="yongh" link="/doucument/documentquery.action"><i class="icon icon-caret-right"></i>数据库恢复</a></li>
            </ul>
        </li>
    </ul>
</div>
<!--sidebar-menu-->
<!--main-container-part-->
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"> <a href="index.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
    </div>
    <!--End-breadcrumbs,这里是该进入后台管理页面的时候就会显示的内容，相当于次首页内容了-->
    <iframe src="jsp/selfmessage.jsp" id="iframe-main" frameborder='0' style="width:100%;"></iframe>
</div>
<!--end-main-container-part-->

<script src="js/excanvas.min.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.ui.custom.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
<script src="js/matrix.js"></script>
<script type="text/javascript">
    //初始化相关元素高度
    function init(){
        $("body").height($(window).height()-80);
        $("#iframe-main").height($(window).height()-90);
        $("#sidebar").height($(window).height()-50);
    }
    //$(fucntion(){});相当于是$(doucument).ready(fucntion(){});的简写
    //加载页面的时候自动执行该部分内容
    $(function(){
        init();
        $(window).resize(function(){
            init();
        });
    });

    // This function is called from the pop-up menus to transfer to
    // a different page. Ignore if the value returned is a null string:
    function goPage (newURL) {
        // if url is empty, skip the menu dividers and reset the menu selection to default
        if (newURL != "") {
            // if url is "-", it is this page -- reset the menu:
            if (newURL == "-" ) {
                resetMenu();
            }
            // else, send page to designated URL
            else {
                document.location.href = newURL;
            }
        }
    }

    // resets the menu selection upon entry to this page:
    function resetMenu() {
        document.gomenu.selector.selectedIndex = 2;
    }

    // uniform使用示例：
    // $.uniform.update($(this).attr("checked", true));
</script>
</body>
</html>