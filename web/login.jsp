<%--
  Created by IntelliJ IDEA.
  User: HH
  Date: 2019/12/11
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div>
    <div class="container" style="width: 30%;height: 40%;margin-top: 50px" align="center">
        <form action="login" name="formname" onsubmit="return false">
            <input type="hidden" name="flag" value="login">
            <div  style="margin-left: 20px;margin-right: 20px;margin-top: 10px" >登录</div>
            <div style="margin-left: 20px;margin-right: 20px;margin-top: 10px">
                <div class="input-group input-group-lg">
                    <span class="input-group-addon">用户名:</span>
                    <input type="text" class="form-control" aria-describedby="sizing-addon1"name="lname" id="ln">
                </div>
                <div class="input-group input-group-lg" style="margin-top: 10px">
                    <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码:</span>
                    <input type="text" class="form-control" aria-describedby="sizing-addon1"name="lpassword" id="lp">
                </div>
            </div>
            <div style="margin-top: 20px;margin-left: 20px;margin-right: 20px" align="center">
                <button class="btn btn-primary btn-block" id="login">登录</button>
            </div>
            <div style="margin-top: 20px">
                <a href="register.jsp" style="margin-left: 30px">注册</a>
                <a href="forget.jsp" >忘记密码</a>
            </div>
        </form>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    jQuery(document).ready(function () {
       $("#login").on("click",function () {
           $.ajax({
               type:"post",
               url:"/login",
               data:{"lname":$("#ln").val(),"lpassword":$("#lp").val(),"flag":"login"},
               success:function (data) {
                alert(data.mess);
                   if(data.mess==="登陆成功") window.location.href="/forum";
               }
           }
        );
       })
    });
</script>
</body>
</html>
