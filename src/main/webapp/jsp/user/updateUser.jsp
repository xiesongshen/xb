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
    *{
        padding: 0;
        margin: 0;
    }
    #box{
        position: relative;
        width: 100%;
        height: 100%;
    }
    form {
        color: white;
        position: absolute;
        width: 80%;

    }

    #head-img{
        position: absolute;
        height: 100px;
        width: 100px;
        float: right;
        border-radius: 50%;
        top: 10%;
        left: 88%;
    }
    #chose{
        position: absolute;
        top: 8%;
        left: 80%;
        font-weight: bold;
    }
</style>
<body>
<%@include file="/jsp/common/top.jsp" %>
<div id="box">
    <form action="/user/updateUser" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${user.id}" id="id">

        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="username" name="username" value="${user.username}">
                <span id="span" style="color: red" hidden>用户名已存在</span>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="password" name="password">
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮箱</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="email" name="email" value="${user.email}">
            </div>
        </div>

        <div class="form-group">
            <label for="realName" class="col-sm-2 control-label">真实姓名</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="realName" name="realName" value="${user.realName}">
            </div>
        </div>

        <div class="form-group">
            <label for="age" class="col-sm-2 control-label">年龄</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="age" name="age" value="${user.age}">
            </div>
        </div>

        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">电话号码</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}">
            </div>

        </div>

        <div style="padding-left: 12%">
            <b style="padding-right: 3%">性别</b>
            <label class="radio-inline">
                <input type="radio" name="gender" id="inlineRadio1" value="1" <c:if test="${user.gender==1}">checked</c:if>>男
            </label>
            <label class="radio-inline">
                <input type="radio" name="gender" id="inlineRadio2" value="0" <c:if test="${user.gender==0}">checked</c:if>>女
            </label>
        </div>


        <div style="width: 50% ;padding-left: 12%;padding-top: 2%">
            <b>个人描述</b>
            <textarea class="form-control" rows="3" name="description" style="margin-left: 13%" >${user.description}</textarea>
        </div>


        <div style="padding-left: 12%;padding-top: 2%">
            <b style="padding-right: 3%">部门</b>
            <select class="form-group-lg" id="deptId" name="deptId" style="color: black">
                <c:forEach var="dept" items="${dept}">
                    <option style="color: black" <c:if test="${user.deptId==dept.id}">selected</c:if> value="${dept.id}" >${dept.name}</option>
                </c:forEach>
            </select><br><br>
        </div>

        <div style="padding-left: 15%">
            <button type="submit" class="btn btn-success">修改</button>
            <button type="reset" class="btn btn-primary">重置</button>
        </div>

        <span id="chose">选择头像</span>
        <img id="head-img" src="/img/getHead?id=${user.id}">
        <!-- 真正的头像图片上传表单 -->
        <input type="file" id="picFile" style="display: none;">
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

    $("#head-img").click(function () {
        $("#picFile").click();
    })

    $(function () {
        $("#picFile").change(function () {

            // 构造文件上传form
            var formData = new FormData();
            formData.append("iconFile", document.getElementById("picFile").files[0]);

            $.ajax({
                url: "/img/upload",
                processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
                contentType: false,       //不对请求头做处理
                type:"post",
                data:formData,
                dataType: "",
                success:function (data) {
                    if(data==1){
                        $("#head-img").attr("src","/img/getHead?id="+$("#id").val()+"&nocache="+new Date().getTime())
                    }else {
                        //上传失败
                    }
                }
            })
        })
    })
</script>

</html>
