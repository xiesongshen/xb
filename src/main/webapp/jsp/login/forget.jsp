<%--
  Created by IntelliJ IDEA.
  User: Xie
  Date: 2020/6/28
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<form class="form-horizontal" method="post" action="/forget/updatePs" style="margin-top: 5%">
    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="username" placeholder="用户名" name="username">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="password" placeholder="password" name="password">
        </div>
    </div>

    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">邮箱</label>
        <div class="col-sm-4">
            <input type="email" class="form-control" id="email" placeholder="email" name="email">
        </div>
        <b id="info" style="color: green" hidden>发送成功</b>
    </div>
    <input id="btn" type="button" value="发送" class="btn btn-default" style="margin-left: 17%;margin-bottom: 1%">

    <div class="form-group">
        <label for="code" class="col-sm-2 control-label">验证码</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="code" placeholder="验证码" name="code">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-4">
            <button type="submit" class="btn btn-success">修改</button>
        </div>
    </div>
</form>

</body>

<script>

    $(function () {
       $("#btn").click(function () {
           $.ajax({
               url:"/forget/email",
               type:"get",
               data:{"email":$("#email").val()},
               dataType:"",
               success:function (data) {
                   if (data==1){
                       $("#info").attr("hidden",false);
                   }else {
                       $("#info").attr("hidden",true)
                   }
               }
           })
       })
    })

</script>
</html>
