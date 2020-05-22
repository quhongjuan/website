<%--
  Created by IntelliJ IDEA.
  User: HH
  Date: 2019/12/18
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘記密碼</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div>
    <div class="container" style="width: 30%;height: 40%;margin-top: 50px" align="center">
        <form action="Forget" name="formname"onsubmit="return false ">
            <div  style="margin-left: 20px;margin-right: 20px;margin-top: 10px" >忘记密码</div>
            <div style="margin-left: 20px;margin-right: 20px;margin-top: 10px">
                <div class="input-group input-group-lg">
                    <span class="input-group-addon">用户名:</span>
                    <input type="text" class="form-control" aria-describedby="sizing-addon1"name="username" id="ln">
                </div>
                <div class="input-group input-group-lg" style="margin-top: 10px">
                    <span class="input-group-addon">邮&nbsp;&nbsp;&nbsp;箱:</span>
                    <input type="text" class="form-control" aria-describedby="sizing-addon1"name="email" id="lp">
                </div>
            </div>
            <div style="margin-top: 20px;margin-left: 20px;margin-right: 20px" align="center">
                <button class="btn btn-primary btn-block" id="sub">提交</button>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    jQuery(document).ready(function () {
        $("#sub").on("click",function () {
            $.ajax({
                    type:"post",
                    url:"/Forget",
                    data:{"username":$("#ln").val(),"email":$("#lp").val()},
                success:function (data) {
                        if(data.mess!==undefined)
                            alert(data.mess);
                       else {
                           alert("发送成功！注意查收邮件");
                           console.log("chengong")
                        }
                    }
                }
            );
        })
    });
</script>
</body>
</html>