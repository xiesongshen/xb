<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎页面</title>
</head>
<style>
    video{
        position: fixed;
        right: 15px;
        bottom: 0px;
        top: 20px;
        min-width: 50%;
        min-height: 50%;
        height: 100%;
        width: 80%;
        /*加滤镜*/
        /*filter: blur(15px); //背景模糊设置 */
        /*-webkit-filter: grayscale(100%);*/
        /*filter:grayscale(100%); //背景灰度设置*/
    }
    source{
        min-width: 100%;
        min-height: 100%;
        height: auto;
        width: auto;
    }

</style>
<body>
<%@include file="top.jsp"%>
<%@include file="left.jsp"%>
<div id="right" style="background-color: black">
    <video id="v1" autoplay loop muted>
        <source src="/doc/1.mp4" type="video/mp4"  />
    </video>
</div>
</body>
</html>
