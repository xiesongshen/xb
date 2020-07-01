<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>会议管理</title>
</head>

<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">
    <a href="/jsp/meeting/addMeeting.jsp" class="btn btn-success"
       style="float: right;margin-top: 2%;margin-right: 1.8%">发布</a>

    <table class="table table-hover" style="margin-top: 8%">
        <tr style="background-color: rgba(255,255,255,.5)">
            <td>会议标题</td>
            <td>部门名称</td>
            <td>会议内容</td>
            <td>会议状态</td>
            <td>开始时间</td>
            <td></td>
        </tr>

        <c:forEach var="meeting" items="${list.data}" varStatus="status">
            <tr class="choseMeeting">
                <td>${meeting.title}</td>
                <td>${meeting.deptName}</td>
                <td>${meeting.content}</td>
                <td>
                    <c:choose>
                        <c:when test="${meeting.status==1}">已开始</c:when>
                        <c:when test="${meeting.status==2}">已结束</c:when>
                        <c:when test="${meeting.status==0}">未开始</c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <fmt:parseDate var="startTime" value="${meeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                    <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td><a class="btn btn-info" href="/meeting/meetingInfo?id=${meeting.id}">查看</a></td>
            </tr>
        </c:forEach>
    </table>

        <div style="margin-top: 13.5%;margin-left: 1%;color: #0f0f0f">
            总页数：${list.pageCount}
            总数据数：${list.count}
            当前页码：${list.page}
            <a  href="/meeting/list?page=${list.page-1>0?list.page-1:1}">上一页</a>
            <a  href="/meeting/list?page=${list.page+1>=list.pageCount?list.pageCount:list.page+1}">下一页</a>

        </div>
</div>
</body>
<script>
</script>
</html>
