<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>top</title>
</head>
<style>
    body {
        background-image: url("/doc/bg1.jpg");
    }

    #top {
        width: 100%;
        height: 10%;
        color: wheat;
    }

    #loginOut {
        float: right;
        margin-top: 0.5%;
        margin-right: 2.6%;
    }
    #headImg{
        position: absolute;
        height: 50px;
        width: 50px;
        float: right;
        border-radius: 50%;

        left: 88%;
    }
    span{
        position: absolute;
        font-size: 40%;
        left: 28%;
        top: 8%;
    }

</style>
<body>
<div id="top">
    <div class="page-header">
        <h1>小标交友
            <small>XiaoBiao Meetting</small>
            <span id="wel">欢迎</span>

            <img id="headImg" src="/img/getHead2">

            <a href="/login/loginOut" id="loginOut" type="button" class="btn btn-default">登出</a>
        </h1>
    </div>
</div>
</body>
<script>
    $(function () {
        $.ajax({
            url:"/login/showName",
            type:"get",
            data:"",
            dataType:"",
            success:function (data) {
                $("#wel").append(data);
            }
        })
    })

    $(function () {
        $.ajax({
            url:"/img/getHead2",
            type:"get",
            data:"",
            dataType:"",
            success:function (data) {
                var number = data.indexOf("http");
                if (number!=-1){
                    $("#headImg").attr("src",data)
                }else {
                    $("#headImg").attr("src","/img/getHead2")
                }
            }
        })
    })
</script>
</html>
