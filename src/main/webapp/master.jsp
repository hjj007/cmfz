<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/black/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            var tb;
            tb = [
                {
                    iconCls: 'icon-save',
                    text: '添加上师',
                    handler: function () {
                        $('#insertDiv').dialog('open');
                    }
                }];


            $("#myDatagrid").datagrid({
                //请求的后台地址
                url: "master/select",
                //设置为true，做分页请求
                pagination: true,
                fit: true,
                fitColumns: true,
                pagination: true,
                pageSize: 2,
                pageList: [2, 4, 6, 8, 10],
                toolbar: tb,
                columns: [[
                    {title: "法名", field: "mdarma", width: 100, align: "center"},
                    {title: "状态", field: "mstatus", width: 100, align: "center"},
                    {title: "图片名", field: "mpic", width: 100, align: "center"},
                ]],

            })
            $("#insertDiv").dialog({
                title: "添加上师",
                width: 300,
                height: 200,
                buttons: "#insertBtn",
                closed: true
            });

        })

        function doInsert() {
            $("#insertDiv").val("")
            $("#insertForm").form("submit", {
                url: "master/insert",
                onSubmit: function () {
                    var isOk = $("#insertForm").form("validate");
                    return isOk;
                },
                success: function (data) {
                    data = JSON.parse(data);
                    if (data.insert) {
                        location.href = '${pageContext.request.contextPath}/master.jsp'
                        $("#insertDiv").dialog("close");
                        $("#myDatagrid").datagrid("load");
                    } else {
                        alert("添加失败,请确认");
                    }
                }
            })
        }

    </script>
</head>
<body>
<table id="myDatagrid"></table>
<div id="insertDiv">
    <form id="insertForm" method="post" enctype="multipart/form-data">
        法名：<input name="mdarma"/><br/>
        头像：<input type="file" name="file"/><br/>
        <br/>
    </form>
</div>
<div id="insertBtn">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="doInsert()">提交</a>
</div>
</form>
</div>
</div>
</div>
</body>
</html>
