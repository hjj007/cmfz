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
                    iconCls: 'icon-tip',
                    text: '专辑详情',
                    handler: function () {
                        var row = $('#myDatagrid').treegrid('getSelected');
                        if (row != null) {
                            if (row.size == null) {
                                $('#dd_album').dialog('open');
                                $('#albumImg').prop("src", "${pageContext.request.contextPath}/lun/" + row.imgpath);
                            } else {
                                alert("请选择专辑")
                            }
                        } else {
                            alert("请选择专辑")
                        }
                    }
                }, '-', {
                    iconCls: 'icon-save',
                    text: '添加专辑',
                    handler: function () {
                        //alert('编辑按钮')
                        $('#insertDiv').dialog('open');

                    }
                }, '-', {
                    iconCls: 'icon-save',
                    text: '添加章节',
                    handler: function () {
                        //alert('编辑按钮')
                        $('#insertDiv1').dialog('open');
                    }
                }, '-', {
                    iconCls: 'icon-undo',
                    text: '下载',
                    handler: function () {
                        //alert('帮助按钮')
                        var row = $("#myDatagrid").datagrid("getSelected");
                        if (row == null) {
                            $.messager.alert('提示消息', '请选择要修改项');
                            return;
                        }
                        var row = $('#myDatagrid').datagrid('getSelected');
                        location.href = "${pageContext.request.contextPath}/chapter/download?id=" + row.id;
                    }
                }];

            $('#myDatagrid').treegrid({
                //后台Controller查询所有专辑以及对应的章节集合
                url: 'album/select',
                idField: 'id',//用来标识标识树节点   主干树与分支树节点  ID不能有相同  并且使用相同的字段  否则ID冲突
                treeField: 'title',//用来定义树节点   树形表格上要展示的信息   注意使用相同的字段 用来展示对应节点名称
                toolbar: tb,
                fit: true,
                fitColumns: true,
                columns: [[
                    {field: 'title', title: '专辑', width: 100},
                    {field: 'size', title: '章节大小', width: 100},
                    {field: 'filename', title: '音乐', width: 100},

                    {field: 'duration', title: '章节的时长', width: 100}

                ]]
            });

            $("#insertDiv").dialog({
                title: "添加专辑",
                width: 300,
                height: 300,
                buttons: "#insertBtn",
                closed: true
            });
            $("#insertDiv1").dialog({
                title: "添加章节",
                width: 300,
                height: 300,
                buttons: "#insertBtn1",
                closed: true
            });

        })

        function doInsert() {
            $("#insertDiv").val("")
            $("#insertForm").form("submit", {
                url: "album/insert",
                onSubmit: function () {
                    var isOk = $("#insertForm").form("validate");
                    return isOk;
                },
                success: function (data) {
                    data = JSON.parse(data);
                    if (data.insert) {
                        location.href = "${pageContext.request.contextPath}/album.jsp";
                        location.href = "${pageContext.request.contextPath}/album/selecta";
                        $("#insertDiv").dialog("close");
                        $("#myDatagrid").datagrid("load");
                    } else {
                        alert("添加失败,请确认");
                    }
                }
            })
        }

        function doInsert1() {

            $("#insertDiv1").val("")
            $("#insertForm1").form("submit", {
                url: "chapter/insert",
                onSubmit: function () {
                    var isOk = $("#insertForm1").form("validate");
                    return isOk;
                },
                success: function (data) {
                    data = JSON.parse(data);
                    if (data.inserta) {
                        location.href = "${pageContext.request.contextPath}/album.jsp";


                        $("#insertDiv1").dialog("close");
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
        专辑：<input name="title"/><br/>
        图片：<input type="file" name="file"/><br/>
        星级：<input name="score"/><br/>
        作者：<input name="author"/><br/>
        播音：<input name="boardcast"/><br/>
        简介：<input name="brief"/><br/>

        <br/>
    </form>
</div>
<div id="dd_album" class="easyui-dialog" title="My Dialog" style="width:600px;height:600px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <img src="" id="albumImg"/>
</div>
<div id="insertBtn">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="doInsert()">提交</a>
</div>

<div id="insertDiv1">
    <form id="insertForm1" method="post" enctype="multipart/form-data">

        <div>
            章节<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        音频：<input class="easyui-filebox" name="file"/>
        <br/>
        专辑：<select type="text" name="albumid">
        <c:forEach var="a" items="${sessionScope.list}">
            <option value="${a.id}">${a.title}</option>
        </c:forEach>
    </select>

    </form>
</div>

<div id="insertBtn1">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="doInsert1()">提交</a>
</div>
</body>
</html>
