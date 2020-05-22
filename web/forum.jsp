<%--
  Created by IntelliJ IDEA.
  User: HH
  Date: 2019/12/10
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        .pright {
            float: right;
        }

        .div-a {
            float: left;
            width: 80%;
        }

        .div-b {
            float: left;
            width: 18%;
        }
    </style>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <button class="btn btn-primary" onclick="location.href='fatopic.jsp'"> 发 帖</button>
        </div>
        <div class="pull-right">
            <form class="form-inline">
                <div class="form-group">
                    <input type="text" class="form-control" id="exampleInputName2" placeholder="搜索主题">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
        </div>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="2">
                <div class="tf">
                    <a id="filter_special" href="forum.jsp" class="showmenu xi2" onclick="showMenu(this.id)">全部主题</a>&nbsp;
                    <a href="forum.jsp" class="xi2">最新</a>&nbsp;
                    <a href="forum.jsp" class="xi2">热门</a>&nbsp;
                </div>
            </th>
            <td>作者</td>
            <td>回复</td>
            <td>发表时间</td>
        </tr>
        </thead>
        <!--循环输出-->
        <form action="" method="post">
            <tbody>
            <c:forEach items="${topicList}" var="topic">
                <tr> 
                    <td></td>
                    <td><a href="/pwInvitation?pid=${topic.getPid()}">${topic.getpText()}</a></td>
                    <td>${topic.getpAuthor()}</td>
                    <td>${topic.getpCount()}</td>
                    <td>${topic.getpTime()}</td>         
                </tr>
            </c:forEach>
            </tbody>
        </form>

    </table>

</div>


<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>


</body>
</html>
