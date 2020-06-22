<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>left</title>
</head>

<style>
    *{
        margin: 0px;
    }
    #left{
        border: 1px solid yellow;
        width: 20%;
        height: 89%;
        float: left;
    }

    ul,li{
        list-style: none;
    }


    #right{
        border: 1px solid gray;
        width: 79%;
        height: 89%;
        float: left;
    }
</style>
<body>
<div id="left">

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
                        html+="</ul>"
                    }
                }*/

                var parent = data.parent;
                var children = data.children;
                for (var i = 0; i < parent.length; i++) {
                    html+=parent[i].name;
                    html+='<ul>';
                    for (var j = 0; j < children.length; j++) {
                        if (children[j].pId == parent[i].id){
                            html += '<li><a href="' + children[j].url + '">' + children[j].name + '</a></li>';
                        }
                    }
                    html+='</ul>';
                }
                $("#left").append(html);
            }
        })
    })
</script>
</html>
