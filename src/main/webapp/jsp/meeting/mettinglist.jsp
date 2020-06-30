<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>会议管理</title>
</head>

<body>
<%@include file="../common/top.jsp"%>
<%@include file="../common/left.jsp"%>
<div id="right">
    <a href="/jsp/user/addUser.jsp" class="btn btn-info" style="float: right;margin-top: 2%;margin-right: 1.8%">添加</a>

    <table class="table table-hover" style="margin-top: 8%">
        <tr style="background-color: rgba(255,255,255,.5)">
            <td>序号</td>
            <td>会议标题</td>
            <td>部门名称</td>
            <td>会议内容</td>
            <td>会议状态</td>
            <td>开始时间</td>
        </tr>

        <c:forEach var="meeting" items="${list}" varStatus="status">
            <tr id="choseMeeting">
                <td>${status.count}</td>
                <td>${meeting.title}</td>
                <td>${meeting.deptName}</td>
                <td>${meeting.content}</td>
                <td>${meeting.status}</td>
                <td>
                    <fmt:formatDate value="${meeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
            </tr>
        </c:forEach>
    </table>

<%--    <div style="margin-top: 20%;margin-left: 1%;color: #0f0f0f">
        总页数：${list.pageCount}
        总数据数：${list.count}
        当前页码：${list.page}
        <a  href="/user/list?page=${list.page-1>0?list.page-1:1}&username=${username}&gender=${gender}&deptId=${deptId}">上一页</a>
        <a  href="/user/list?page=${list.page+1>=list.pageCount?list.pageCount:list.page+1}&username=${username}&gender=${gender}&deptId=${deptId}">下一页</a>

    </div>--%>
</div>
</body>
<script>
</script>
</html>
