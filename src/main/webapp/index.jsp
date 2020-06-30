<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript" src="${path}/statics/js/jquery-3.4.1.js"></script>
<script src="${path}/statics/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

<link rel="stylesheet" href="${path}/statics/bootstrap-3.3.7-dist/css/bootstrap.css">
<html>
<style>

    #BigBox {
        background-color: rgba(245,245,220,.4);
        height: 50%;
        width: 30%;
        position: absolute;
        top: 20%;
        left: 35%;
        border-radius: 5%;
        color: black;
    }

    #login {
        width: 120%;
        height: auto;
        margin-left: 5%;
        margin-top: 10%;
    }

    #top {
        color: wheat;
        width: 100%;
        height: 10%;
    }


    #addBtn {
        float: right;
        margin-right: 5%;
    }
    *{
        margin: 0px;
        padding: 0px;

    }
    body{
        background-image: url("/doc/bg2.jpg");
    }
    #wx{
        position: absolute;
        left: 73%;
        top: 80%;
    }
 /*   video{
        position: fixed;
        right: 0px;
        bottom: 0px;
        min-width: 100%;
        min-height: 100%;
        height: auto;
        width: auto;
        !*加滤镜*!
        !*filter: blur(15px); //背景模糊设置 *!
        !*-webkit-filter: grayscale(100%);*!
        !*filter:grayscale(100%); //背景灰度设置*!
        z-index:-11
    }
    source{
        min-width: 100%;
        min-height: 100%;
        height: auto;
        width: auto;
    }*/
</style>

<body>
<%--<video id="v1" autoplay loop muted>
    <source src="/doc/1.mp4" type="video/mp4"  />
</video>--%>
<div id="top">
    <div class="page-header">
        <h1>小标会议
            <small>XiaoBiao Meetting</small>
            <a href="/jsp/user/add.jsp" id="addBtn" type="button" class="btn btn-info" >注册</a>
        </h1>
    </div>
</div>
<div id="BigBox">
    <div>
        <form class="form-horizontal" method="post" action="/login/login" id="login">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputEmail3" placeholder="用户名" name="username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="password"
                           name="password">
                </div>
            </div>
            <div class="form-group">
                <label for="code" class="col-sm-2 control-label">验证码</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="code" placeholder="验证码" name="code">
                </div>
                <div class="col-sm-3">
                    <img src="/img/getCode" id="imgCode">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="1" name="remember"> 记住我
                        </label>
                        <a class="btn" href="/jsp/login/forget.jsp" style="margin-left: 20%">忘记密码?</a>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-success">登陆</button>
                </div>
            </div>
        </form>
        <a href="/otherLogin/weChatLogin"><img id="wx" src="/doc/wx.png"></a>
    </div>
</div>

</body>
<script>
    $(function () {
        $("#imgCode").click(function () {
            $(this).attr("src", "/img/getCode?nocache=" + new Date().getTime())
        })

    })
</script>

</html>
