<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
</head>

<body>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="page-header">
                <h1>
                    兼职审核页面 <small>信息审核</small>
                </h1>
            </div>
            <h4>
                兼职标题
            </h4>
            <p>
                   ${job.title}
            </p>
            <h4>
                发布者
            </h4>
            <p>
                ${job.username}
            </p>
            <h4>
                发布者联系电话
            </h4>
            <p>
                ${job.phonenum}
            </p>
            <dl>
                <dt>
                    薪水
                </dt>
                <dd>
                    ${job.paymoney}
                </dd>
                <dt>
                    结算时间
                </dt>
                <dd>
                    ${job.payway}
                </dd>
                <dt>
                    工作时间段
                </dt>
                <dd>
                    ${job.worktime}
                </dd>
                <dt>
                    招聘结束时间
                </dt>
                <dd>
                    ${job.beigintime}
                </dd>
            </dl>
            <dl>
                <dt>
                    招聘人数
                </dt>
                <dd>
                    ${job.peoplenun}
                </dd>
                <dt>
                    工作开始结束时间
                </dt>
                <dd>
                    ${job.bftime}
                </dd>
                <dt>
                    兼职类型
                </dt>
                <dd>
                    ${job.jobtype}
                </dd>
                <dt>
                    工作地点
                </dt>
                <dd>
                    ${job.workplace}
                </dd>
                <dt>
                    性别要求
                </dt>
                <dd>
                    ${job.gender}
                </dd>
            </dl>
            <h4>
                工作描述
            </h4>
            <p>
                ${job.workdescribe}
            </p>
            <div class="row-fluid">
                <div class="span4">
                </div>
                <div class="span4">
                    <a id="modal-546401" href="#modal-container-546401" role="button" class="btn" data-toggle="modal">审核不通过</a>

                    <div id="modal-container-546401" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3 id="myModalLabel">
                                审核确认
                            </h3>
                        </div>
                        <div class="modal-body">
                            <p>
                                该兼职信息审核不通过
                            </p>
                            <input placeholder="请填写审核原因" id="sureresult" type="text">
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn btn-primary" onclick="nosure()">确认审核</button>
                        </div>
                    </div>
                </div>
                <div class="span4">
                    <a id="modal-788360" href="#modal-container-788360" role="button" class="btn" data-toggle="modal">审核通过</a>

                    <div id="modal-container-788360" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3 id="myModalLabel2">
                                审核确认
                            </h3>
                        </div>
                        <div class="modal-body">
                            <p>
                                该兼职信息审核通过
                            </p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn btn-primary" onclick="yessure()">审核通过</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row-fluid">
                <div class="span12">
                    <div class="alert">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <h4>
                            提示!
                        </h4> <strong>友情提示!</strong> 审核不通过的情况下，请注明审核未通过的原因！
                    </div>
                </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    function nosure(){
        /*alert("触发事件成功");*/
        $.ajax({
            data: {sureresult:$("#sureresult").val(), jobid:${job.id}},
            type:"post",
            dataType:'json',
            url:"JobServlet/jobnosure",
            error:function (data) {
                alert("出现些问题");
                window.location.href = "JobServlet/jobstatequery"
            },
            success:function(data){
                var msg1=data["msg"];
                if(msg1=="success")
                {
                    window.location.href = "JobServlet/jobstatequery"
                }
                else{
                    window.location.href = "JobServlet/jobstatequery"
                }

            }
        });
    }
    function yessure(){
        /*alert("触发事件成功");*/
        $.ajax({
            data: {jobid:${job.id}},
            type:"post",
            dataType:'json',
            url:"JobServlet/joboksure",
            error:function (data) {
                alert("出现些问题");
                window.location.href = "JobServlet/jobstatequery"
            },
            success:function(data){
                var msg1=data["msg"];
                /*alert(msg1);*/
                if(msg1=="success")
                {
                    window.location.href = "JobServlet/jobstatequery"
                }
                else{
                    window.location.href = "JobServlet/jobstatequery"
                }

            }
        });
    }
</script>
</body>
</html>