<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户管理</title>
</head>

<body>
<%@include file="../common/top.jsp"%>
<%@include file="../common/left.jsp"%>
<div id="right">
    <a href="/jsp/user/addUser.jsp" class="btn btn-info" style="float: right;margin-top: 2%;margin-right: 1.8%">添加</a>
    <form action="/user/list" method="post" class="form-inline" style="float: left;margin-top: 2%;">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" class="form-control" id="username" name="username" value="${username}">
        </div>

        <div class="form-group">
            <label for="gender">性别</label>
            <select id="gender" name="gender" class="form-control">
                <option value="-1">请选择</option>
                <option value="1"<c:if test="${gender=='1'}">selected</c:if>>男</option>
                <option value="0"<c:if test="${gender=='0'}">selected</c:if>>女</option>
            </select>
        </div>

        <div class="form-group">
            <label for="dept">部门</label>
            <select id="dept" name="deptId" class="form-control">
            </select>
        </div>
        <button type="submit" class="btn btn-default">查找</button>
    </form>

    <table class="table table-hover" style="margin-top: 8%">
        <tr style="background-color: rgba(255,255,255,.5)">
            <td>序号</td>
            <td>用户名</td>
            <td>邮箱</td>
            <td>真实姓名</td>
            <td>年龄</td>
            <td>手机号</td>
            <td>性别</td>
            <td>注册时间</td>
            <td>部门</td>
            <td>操作</td>
        </tr>

        <c:forEach var="user" items="${list.data}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.realName}</td>
                <td>${user.age}</td>
                <td>${user.phone}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.gender==1}">男</c:when>
                        <c:when test="${user.gender==0}">女</c:when>
                        <c:otherwise>未知</c:otherwise>
                    </c:choose>

                </td>
                <td>
                    <fmt:formatDate value="${user.registerTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"></fmt:formatDate>
                </td>
                <td>${user.deptName}</td>
                <td>
                    <a href="/user/toUpdateUser?id=${user.id}" class="btn btn-warning">修改</a>
                    <a href="/user/del?id=${user.id}" class="btn btn-danger">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div style="margin-top: 20%;margin-left: 1%;color: #0f0f0f">
        总页数：${list.pageCount}
        总数据数：${list.count}
        当前页码：${list.page}
        <a  href="/user/list?page=${list.page-1>0?list.page-1:1}&username=${username}&gender=${gender}&deptId=${deptId}">上一页</a>
        <a  href="/user/list?page=${list.page+1>=list.pageCount?list.pageCount:list.page+1}&username=${username}&gender=${gender}&deptId=${deptId}">下一页</a>

    </div>
</div>
</body>
<script>
    $(function () {
        $.ajax({
            url: "/dept/listDept",
            type: "get",
            data: "",
            dataType: "json",
            success: function (data) {
                var html = '<option value="-1">请选择</option>';
                for (let i = 0; i < data.length; i++) {

                    if (data[i].id == ${deptId}){
                        html += '<option value="' + data[i].id + '" selected >' + data[i].name + '</option>';
                    }else {
                        html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                }

                $("#dept").append(html);
            }
        })
    })
</script>
</html>
