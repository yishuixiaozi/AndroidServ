<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>兼职信息界面</title>
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
    <link rel="stylesheet" href="css/uniform.css" />
    <link rel="stylesheet" href="css/select2.css" />
    <link rel="stylesheet" href="css/matrix-style2.css" />
    <link rel="stylesheet" href="css/matrix-media.css" />
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>

<body>
<div id="content">
    <div id="content-header">
        <h1>数据备份</h1>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
                        <h5>数据备份信息显示</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form action="/user/deletemore.action" method="post">
                            <table class="table table-bordered data-table">
                                <thead>
                                <tr>
                                    <th>备份人</th>
                                    <th>备份时间</th>
                                    <th>文件名</th>
                                    <th>备份地址</th>
                                    <th>备份说明</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="job" items="${backuplist}">
                                    <tr class="gradeX">
                                        <td>${job.backupusername}</td>
                                        <td>${job.backuptime}</td>
                                        <td>${job.backupfilename}</td>
                                        <td>${job.backupfilepath}</td>
                                        <td>${job.remark}</td>
                                        <td><a href="BackupServlet/recover?backupfilename=${job.backupfilename}"
                                               style="color: #0e90d2">恢复到该备份内容</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <%--<a href="BackupServlet/backup">备份最新数据</a>--%>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="span4">
        <a id="modal-788360" href="#modal-container-788360" role="button" class="btn" data-toggle="modal">数据备份</a>
        <div id="modal-container-788360" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel2">
                    备份说明
                </h3>
            </div>

            <div class="modal-body">
                <p>
                    您将备份数据库数据内容
                </p>
                <input placeholder="请填写备份说明" id="sureresult" type="text">
            </div>

            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                <button class="btn btn-primary" onclick="yessure()">确认备份</button>
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
            </h4> <strong>友情提示!</strong> 建议恢复数据为最新内容
        </div>
    </div>
</div>
</div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/jquery.ui.custom.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.uniform.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/matrix.js"></script>
<script src="js/matrix.tables.js"></script>

<script type="text/javascript">
    function yessure(){
        /*alert("触发事件成功");*/
        $.ajax({
            data: {remark:$("#sureresult").val()},
            type:"post",
            dataType:'json',
            url:"BackupServlet/backup1",
            error:function (data) {
                alert("出现些问题");
                window.location.href = "BackupServlet/querybackup"
            },
            success:function(data){
                var msg1=data["msg"];
               /* alert("数据备份完成")*/
                if(msg1=="success")
                {
                    window.location.href = "BackupServlet/querybackup"
                }
                else{
                    window.location.href = "BackupServlet/querybackup"
                }

            }
        });
    }
</script>
</body>

</html>