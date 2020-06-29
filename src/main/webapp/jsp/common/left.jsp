<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>left</title>
</head>

<style>
    *{
        padding: 0;
        margin: 0;
    }
    #left{

        width: 15%;
        height: 83%;
        float: left;
        font-size: 120%;
        color: wheat;

    }


    ul,li{
        list-style: none;
    }


    #right{
        background-color:rgba(	245,245,245,.5);
        width: 84%;
        height: 83%;
        float: left;
        color: white;
    }
    #pro{
        margin-left: 20%;
    }
    b{

        margin-left: 5%;
    }
    #box{
        margin-top: 8%;
    }
</style>
<body>
<div id="left">
    <div id="box"></div>
</div>
</body>
<script>
    $(document).ready(function () {

        $.ajax({
            url:"/menuServlet",
            type:"get",
            data:"",
            dataType:"json",
            success:function (data) {
                var html='';
               /* for (var i = 0; i < data.length; i++) {
                    if (data[i].type == 1){
                        html+=data[i].name;
                        html+='<ul>';
                        for (var j = 0; j < data.length; j++) {

                            if (data[j].pId == data[i].id) {
                                html += '<li><a href="' + data[j].url + '">' + data[j].name + '</a></li>';
                            }
                        }
                        html+="</ul>"0
                    }
                }*/

                var parent = data.parent;
                var children = data.children;
                for (var i = 0; i < parent.length; i++) {
                    html+='<b>'+parent[i].name+'</b>';
                    html+='<ul class="nav nav-pills nav-stacked">';
                    for (var j = 0; j < children.length; j++) {
                        if (children[j].pId == parent[i].id){
                            html += '<li id="pro"role="presentation"><a style="color: white" href="' + children[j].url + '">' + children[j].name + '</a></li>';
                        }
                    }
                    html+='</ul>';
                }
                $("#box").append(html);
            }
        })


    })

</script>

<script>
    $("#pro").click(function () {
        alert("aa")
    })
</script>
</html>
