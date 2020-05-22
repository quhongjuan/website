<%@ page import="javafx.scene.control.Alert" %><%--
  Created by IntelliJ IDEA.
  User: HH
  Date: 2019/12/11
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div>
    <div class="container" style="width: 30%;height: 40%;margin-top: 50px" align="center">
        <form action="login" name="formname" method="post" onsubmit="return false;">
            <input type="hidden" name="flag"  value="register">
            <div  style="margin-left: 20px;margin-right: 20px;margin-top: 10px" >注册</div>
            <div style="margin-left: 20px;margin-right: 20px;margin-top: 10px">
                <div class="input-group input-group-lg">
                    <span class="input-group-addon"> 用户名:</span>
                    <input type="text" class="form-control" aria-describedby="sizing-addon1"id="na" name="rname">
                </div>
                <div class="input-group input-group-lg" style="margin-top: 10px">
                    <span class="input-group-addon" >密&nbsp;&nbsp;&nbsp;&nbsp;码:</span>
                    <input type="text" class="form-control" aria-describedby="sizing-addon1"id="rp"name="rpassword">
                </div>
                <div class="input-group input-group-lg" style="margin-top: 10px">
                    <span class="input-group-addon" >确认密码:</span>
                    <input type="text" class="form-control" aria-describedby="sizing-addon1"id="dp"name="dpassword">
                </div>
                <div class="input-group input-group-lg" style="margin-top: 10px">
                    <span class="input-group-addon">注册邮箱:</span>
                    <input type="text" class="form-control" aria-describedby="sizing-addon1"id="re" name="remail">
                </div>
            </div>
            <div style="margin-top: 20px;margin-left: 20px;margin-right: 20px" align="center">
                <button class="btn btn-primary btn-block" id="login">注册</button>
            </div>
            <div style="margin-top: 20px">
                <a href="login.jsp" style="margin-left: 30px">登录</a>
            </div>
        </form>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    jQuery(document).ready(function () {
        $("#ra").val("");$("#rp").val("");
        $("#dp").val("");$("#re").val("");
        $("#login").on("click",function () {
            if((($("#ra").val()=="")||($("#rp").val()=="")||($("#dp").val()=="")||($("#re").val()==""))){
                alert("请把信息填全！");
                console.log("失败");
                return;
            }
            if($("#rp").val()!==$("#dp").val()){
                alert("两次输入密码不一致，请重新输入！");
                return ;
            }
            else{
                $.ajax({
                    type: "post",
                    url: "/login",
                    data:{"rname":$("#na").val(),"rpassword":$("#rp").val(),"remail":$("#re").val(),"flag":"register"},
                    success: function (data) {
                        alert(data.mess);
                        if(data.mess==="注册成功") window.location.href="login.jsp";
                    }
                });
            }

        });

    });

</script>
</body>
</html>

