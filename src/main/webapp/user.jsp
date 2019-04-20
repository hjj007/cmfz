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
                    iconCls: 'icon-add',
                    text: '注册',
                    handler: function () {
                        $('#insertDiv').dialog('open');
                    }
                }, '-',
                {
                    iconCls: 'icon-no',
                    text: '冻结',
                    handler: function () {
                        var row = $("#myDatagrid").datagrid("getSelected");
                        if (row == null) {
                            $.messager.alert('提示', '请选择冻结人员');
                        } else {
                            if (row.status == 0) {
                                $.messager.alert('提示', '已经冻结，请重新选择');
                            } else {
                                location.href = '${pageContext.request.contextPath}/user/up?id=' + row.id

                            }
                        }
                    }
                }
                , '-',
                {
                    iconCls: 'icon-ok',
                    text: '解冻',
                    handler: function () {
                        var row = $("#myDatagrid").datagrid("getSelected");
                        if (row == null) {
                            $.messager.alert('提示', '请选择冻结人员');
                        } else {
                            if (row.status == 1) {
                                $.messager.alert('提示', '该用户未被冻结，请重新选择');
                            } else {
                                location.href = '${pageContext.request.contextPath}/user/up?id=' + row.id

                            }
                        }
                    }
                }
            ];
            $("#myDatagrid").datagrid({
                url: "user/select",
                toolbar: tb,
                columns: [[
                    {title: "编号", field: "id", width: 81, align: "center"},//一个{}表示的是一列
                    {title: "用户名", field: "name", width: 81, align: "center"},
                    {title: "法号", field: "dharma", width: 81, align: "center"},
                    {
                        title: "性别", field: "sex", width: 81, align: "center",
                        formatter: function (value, rowData, rowIndex) {
                            if (value == 0) {
                                return "女";
                            } else {
                                return "男"
                            }
                        }
                    },
                    {title: "省分", field: "province", width: 81, align: "center"},
                    {title: "市县", field: "city", width: 81, align: "center"},
                    {title: "签名", field: "sign", width: 81, align: "center"},
                    {
                        title: "状态", field: "status", width: 81, align: "center",
                        formatter: function (value1, rowData, rowIndex) {
                            if (value1 == 0) {
                                return "冻结";
                            } else {
                                return "未冻结";
                            }
                        }
                    },
                    {title: "电话", field: "phone", width: 81, align: "center"},
                    {title: "密码", field: "password", width: 81, align: "center"},
                    {title: "头像名", field: "pic", width: 81, align: "center"},
                    {title: "注册日期", field: "date", width: 81, align: "center"}
                ]],
                view: detailview,
                //rowIndex:行的索引
                //rowData ：行数据
                detailFormatter: function (rowIndex, rowData) {
                    return '<table><tr>' +
                        '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/lun/' + rowData.pic + '" style="height:100px;width:100px"></td>' +
                        '<td style="border:0">' +
                        '</td>' +
                        '</tr></table>';
                }
            })
            $("#insertDiv").dialog({
                title: "添加专辑",
                width: 300,
                height: 300,
                buttons: "#insertBtn",
                closed: true
            });
        })

        function doInsert() {
            //alert('1111111111111111111')
            $("#insertDiv").val("")
            $("#insertForm").form("submit", {
                url: "user/inster",
                onSubmit: function () {
                    var isOk = $("#insertForm").form("validate");
                    return isOk;
                },
                success: function (data) {
                    data = JSON.parse(data);
                    if (data.insert) {
                        location.href = "${pageContext.request.contextPath}/user.jsp";
                        $.messager.alert("提示", '添加成功'),
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
        名字：<input name="name"/><br/>
        法号：<input name="dharma"/><br/>
        性别：<input type="radio" value="1" name="sex"/>男
        <input type="radio" value="0" name="sex"/>女
        <br/>
        头像：<input type="file" name="file"/><br/>
        老师：<select type="text" name="masterid">
        <c:forEach var="a" items="${sessionScope.list}">
            <option value="${a.mid}">${a.mdarma}</option>
        </c:forEach>
    </select>
        <br/>
        省份：<input name="province"/><br/>
        市县：<input name="city"/><br/>
        个签：<input name="sign"/><br/>
        手机：<input name="phone"/><br/>
        密码：<input name="password"/><br/>
        <br/>
    </form>
</div>
<div id="insertBtn">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="doInsert()">提交</a>
</div>
</body>
</html>
