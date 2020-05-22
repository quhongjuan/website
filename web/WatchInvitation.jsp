<%--
  Created by IntelliJ IDEA.
  User: HH
  Date: 2019/12/15
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/summernote/summernote.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div id="postlist" style="border: 1px solid #CDCDCD;background: #FFF;">
            <div style="padding:10px 20px">
            <b style="font-size: 18px">${title}</b>
            </div>
        <c:forEach items="${invitations}" var="invitation" varStatus="status">
            <table>
                <tr>
                    <td style="background-color: #C2D5E3;height: 4px;border-right: 1px solid #C2D5E3;"></td>
                    <td style="background-color: #E5EDF2;height: 4px"></td>
                </tr>
                <tr>
                    <td rowspan="2"
                        style="vertical-align: top;background: #E5EDF2;border-right: 1px solid #C2D5E3;">
                        <div style="border-bottom: 1px dashed #CDCDCD;padding:10px 0 10px 15px;">
                            <b>${invitation.tAuthor}</b>
                        </div>
                        <div style="padding: 32px">
                            <img src="assets/imge/write.png" width="144px" height="144px"
                                 style="border: solid 4px white"/>
                        </div>
                    </td>
                    <td style="vertical-align: top;padding: 0 20px;width:90%">
                        <div style="border-bottom: 1px dashed #CDCDCD;padding:10px 0">
                            发表时间：${invitation.gettTime()}
                            <div class="pull-right">
                                <c:choose>
                                    <c:when test="${status.index==0}">楼主</c:when>
                                    <c:when test="${status.index==1}">沙发</c:when>
                                    <c:when test="${status.index==2}">板凳</c:when>
                                    <c:when test="${status.index==3}">地板</c:when>
                                    <c:otherwise>${status.index+1}楼</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div style="padding-top: 10px;">
                            <div>
                                    ${invitation.gettText()}
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </c:forEach>
    </div>
    <form action="pwInvitation" method="post">
        <input type="hidden" name="flag" value="write">
        <div class="form-group" style="margin-top: 10px">
            <h2>发帖回复</h2>
            <textarea id="summernote" name="editordata"></textarea>
            <input type="hidden" name="pid" value="${pid}">
            <button class="btn btn-primary">发表回复</button>
        </div>
    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="assets/summernote/summernote.min.js"></script>
<script src="assets/summernote/lang/summernote-zh-CN.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#summernote').summernote({
            lang: 'zh-CN',
            height: '300px'
        });
    });
</script>
</body>
</html>
