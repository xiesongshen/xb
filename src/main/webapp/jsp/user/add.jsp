<%--
  Created by IntelliJ IDEA.
  User: Xie
  Date: 2020/6/23
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<style>
    body {
        background-image:url("/doc/bg3.jpg");
    }
    #box{
        position: relative;
        width: 50%;
        height: 80%;
        background-color:rgba(	169,169,169,.5);
        margin-left: 25%;
        border-radius: 5%;
    }
    form {
        padding-top: 5%;
        padding-left: 5%;
        position: absolute;
        width: 90%;
    }
    #loginBtn {
        float: right;
        margin-right: 5%;
    }
    #top {
        width: 100%;
        height: 10%;
        color: wheat;
    }
</style>
<body>
<div id="top">
    <div class="page-header">
        <h1>小标交友
            <small>XiaoBiao Meetting</small>
            <a href="/index.jsp" id="loginBtn" type="button" class="btn btn-info" >登录</a>
        </h1>
    </div>
</div>
<div id="box">
    <form action="/login/add" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" name="username">
                <span id="span" style="color: red" hidden>用户名已存在</span>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="password" name="password">
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮箱</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="email" name="email">
            </div>
        </div>

        <div class="form-group">
            <label for="realName" class="col-sm-2 control-label">真实姓名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="realName" name="realName">
            </div>
        </div>

        <div class="form-group">
            <label for="age" class="col-sm-2 control-label">年龄</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="age" name="age">
            </div>
        </div>

        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">电话号码</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="phone" name="phone">
            </div>

        </div>

        <div style="padding-left: 12%">
            <b style="padding-right: 3%">性别</b>
            <label class="radio-inline">
                <input type="radio" name="gender" id="inlineRadio1" value="1">男
            </label>
            <label class="radio-inline">
                <input type="radio" name="gender" id="inlineRadio2" value="0">女
            </label>
        </div>

        <div style="width: 50% ;padding-left: 12%;padding-top: 2%">
            <b>个人描述</b>
            <textarea class="form-control" rows="3" name="description" style="margin-left: 13%"></textarea>
        </div>


        <div style="padding-left: 12%;padding-top: 2%">
            <b style="padding-right: 3%">部门</b>
            <select class="form-group-lg" id="dept">
            </select><br><br>
        </div>

        <div style="padding-left: 15%">
            <button type="submit" class="btn btn-success">注册</button>
            <button type="reset" class="btn btn-primary">重置</button>
        </div>
    </form>
</div>
</body>
<script>
    $("#username").blur(function () {
        $.ajax({
            url: "/user/findByN",
            type: "get",
            data: {"username": $("#username").val()},
            dataType: "",
            success: function (data) {
                if (data==1){
                    $("#span").attr("hidden",false);
                }else {
                    $("#span").attr("hidden",true);
                }
            }
        })
    })

    $(function () {
        $.ajax({
            url: "/dept/listDept",
            type: "get",
            data: "",
            dataType: "json",
            success: function (data) {
                var html = '<option value="-1">请选择</option>';
                for (let i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }

                $("#dept").append(html);
            }
        })
    })
</script>
</html>
