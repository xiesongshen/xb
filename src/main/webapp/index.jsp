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
    #BigBox{
        height: 40%;
        width: 40%;
        position: absolute;
        top: 20%;
        left: 28%;
    }
    #login{

        width: 100%;
        height: auto;
        margin-left: 21%;
        margin-top: 10%;
    }
</style>

<body id="BigBox">
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
                <input type="password" class="form-control" id="inputPassword3" placeholder="password" name="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="1" name="remember"> 记住我
                    </label>
                </div>
            </div>
        </div>
        <div>
            <a class="btn" href="/jsp/login/forget.jsp" style="margin-left: 48%">忘记密码?</a>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" class="btn btn-default">登陆</button>
            </div>
        </div>
    </form>
</div>
</body>

</html>
