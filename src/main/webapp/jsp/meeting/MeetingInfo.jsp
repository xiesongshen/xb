<%--
  Created by IntelliJ IDEA.
  User: Xie
  Date: 2020/7/1
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会议信息</title>
</head>
<body>
<%@include file="../common/top.jsp"%>
<%@include file="../common/left.jsp"%>
<div id="right">
    <form action="/meeting/list" method="post" class="form-horizontal" style="margin-top: 8%;color: black">

        <div style="position: absolute;top: 15.2%;">
            <button type="submit" class="btn btn-warning">返回</button>
        </div>

        <input type="hidden" name="id" value="${info.id}" id="id">

        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">会议标题</label>
            <div class="col-sm-5">
                <input readonly type="text" class="form-control" id="title" name="title" value="${info.title}">
            </div>
        </div>

        <div class="form-group">
            <label for="content" class="col-sm-2 control-label">会议内容</label>
            <div class="col-sm-5">
                <textarea readonly type="text" rows="3" class="form-control" id="content" name="content">${info.content}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label for="publishDate" class="col-sm-2 control-label">发布时间</label>
            <div class="col-sm-2">
                <fmt:formatDate var="publishTime" value="${info.publishDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                <input type="text" readonly class="form-control" id="publishDate" name="publishDate" value="${publishTime}">
            </div>
        </div>

        <div class="form-group">
            <label for="startTime" class="col-sm-2 control-label">开始时间</label>
            <div class="col-sm-2">

                <fmt:parseDate var="startTime" value="${info.startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                <fmt:formatDate var="sTime" value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                <input type="text" readonly class="form-control" id="startTime" name="startTime" value="${sTime}">
            </div>
        </div>

        <div class="form-group">
            <label for="endTime" class="col-sm-2 control-label">结束时间</label>
            <div class="col-sm-2">
                <fmt:parseDate var="endTime" value="${info.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                <fmt:formatDate var="eTime" value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                <input type="text" readonly class="form-control" id="endTime" name="endTime" value="${eTime}">

            </div>
        </div>

        <div class="form-group">
            <label for="deptName" class="col-sm-2 control-label">会议部门</label>
            <div class="col-sm-1">
                <input readonly type="text" class="form-control" id="deptName" name="deptName" value="${info.deptName}">
            </div>
        </div>


        <div class="form-group">
            <label for="status" class="col-sm-2 control-label">会议状态</label>
            <div class="col-sm-1">
                <input type="text" readonly class="form-control" id="status" name="status" value="${info.status}">
            </div>
        </div>

    </form>
</div>

</body>
</html>
