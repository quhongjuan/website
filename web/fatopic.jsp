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

    <div class="row">
        <div class="col-md-3">
            <h3 ><img width="30" height="30" src="assets/imge/write.png"><span> 发表新帖</span> </h3>
        </div>
        <div class="pull-right">
            <p class="pright">发贴请遵守贴吧协议及“七条底线”</p>
        </div>
    </div>
    <form action="publishTopic" name="formname" method="post" >
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon" id="sizing-addon2">话题</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon2"name="topic">
                    </div>
                </div>
            </div>
        </div>
        <textarea name="textContext" id="summernote" name="editordata"></textarea>
        <button type="submit" class="btn btn-primary">发表</button>
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
