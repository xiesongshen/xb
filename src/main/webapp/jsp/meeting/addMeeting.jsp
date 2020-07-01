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
    <title>添加会议</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">
    <form action="/meeting/addMeeting" method="post" class="form-horizontal" style="margin-top: 5%;color: black">

        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">会议标题</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="title" name="title">
            </div>
        </div>

        <div class="form-group">
            <label for="content" class="col-sm-2 control-label">会议内容</label>
            <div class="col-sm-5">
                <textarea type="text" rows="3" class="form-control" id="content" name="content"></textarea>
            </div>
        </div>


        <div class="form-group">
            <label for="startTime" class="col-sm-2 control-label">开始时间</label>
            <div class="col-sm-2">
                <input type="datetime-local" class="form-control" id="startTime" name="startTime" value="${startTime}">
            </div>
        </div>

        <div class="form-group">
            <label for="endTime" class="col-sm-2 control-label">结束时间</label>
            <div class="col-sm-2">
                <input type="datetime-local" class="form-control" id="endTime" name="endTime">

            </div>
        </div>

        <div style="padding-left: 7%;padding-top: 2%">
            <b style="padding-right: 3%">部门</b>
            <select class="form-group-lg" id="deptId" name="deptId">
            </select><br><br>
        </div>

        <div style="margin-left: 6%;padding-top: 2%">
            <b style="padding-right: 3%">参会人</b>
            <select class="form-group-lg" id="makeUsers" name="makeUsers">
            </select><br><br>
        </div>

        <div style="margin-left: 16.5%;margin-top: 5%">
            <button type="submit" class="btn btn-info">添加</button>
            <button type="reset" class="btn btn-info" style="margin-left: 2%">重置</button>
        </div>

    </form>
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
                    html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
                $("#deptId").append(html);
                //刷新页面ui
                $("#deptId").selectpicker("refresh");
            }
        })
    });

    //根据部门id查询该部门下的所有用户
    $("#deptId").change(function () {
        $("#makeUsers").empty();
        var deptId = $(this).val();
        if (deptId == null) {
            return false;
        }
        $.ajax({
            url: "/user/findUserByDeptId",
            type: "get",
            data: {deptId: deptId},
            dataType: "json",
            success: function (data) {
                var html = '<option>请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].realName + '</option>';
                }
                $("#makeUsers").append(html);
                //刷新页面ui
                $("#makeUsers").selectpicker("refresh");
            }
        });
    });
</script>
</html>
