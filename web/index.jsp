<%--
  Created by IntelliJ IDEA.
  User: HH
  Date: 2019/12/17
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setStatus(302);
    response.setHeader("location",request.getSession().getAttribute("username")==null?"login.jsp":"forum");
 %>