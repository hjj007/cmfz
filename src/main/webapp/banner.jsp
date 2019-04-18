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
            var tb = [{
                iconCls: 'icon-add',
                text: '添加',
                handler: function () {
                    //alert('编辑按钮')
                    $("#insertDiv").dialog("open");

                }
            }, '-', {
                iconCls: 'icon-edit',
                text: '修改',
                handler: function () {
                    var selectedRow = $("#myDatagrid").datagrid("getSelected");
                    if (selectedRow == null) {
                        $.messager.alert('提示消息', '请选择要修改项');
                        return;
                    }
                    $("#updateDiv").dialog("open");
                    //带上原数据
                    $("#updateForm").form('load', selectedRow)
                    //alert('帮助按钮')

                }
            }, '-', {
                iconCls: 'icon-remove',
                text: '删除',
                handler: function () {
                    $("#myDatagrid").edatagrid("destroyRow");
                }
            }, '-', {
                iconCls: 'icon-save',
                text: '保存',
                handler: function () {
                    //alert('帮助按钮')
                    $('#myDatagrid').edatagrid('saveRow');
                }
            }];
            //修改对话框
            $("#updateDiv").dialog({
                title: "修改对话框",
                width: 300,
                height: 200,
                buttons: "#updateBtn",
                closed: true
            })
            //修改对话框===END===

            $("#insertDiv").dialog({
                title: "添加对话框",
                width: 300,
                height: 200,
                buttons: "#insertBtn",
                closed: true
            });
            $("#myDatagrid").edatagrid({
                //请求的后台地址
                url: "banner/select",
                updateUrl: '${pageContext.request.contextPath}/banner/update',
                destroyUrl: 'banner/delete',
                onDblClickRow: function (rowIndex, rowData) {
                    //打开修改对话框，把要修改的内容写入到修改对话框中
                    toOpenUpdateDialog(rowData);
                },
                //设置为true，做分页请求
                pagination: true,
                fit: true,
                fitColumns: true,
                pagination: true,
                pageSize: 2,
                pageList: [2, 4, 6, 8, 10],
                toolbar: tb,
                columns: [[
                    {title: "图名", field: "title", width: 100, align: "center"},
                    {title: "图片", field: "imgpath", width: 100, align: "center"},
                    {title: "发布时间", field: "creatdate", width: 100, align: "center"},
                    {
                        field: 'status', title: '状态', width: 100, align: "center", editor: {
                            type: 'text',
                            options: {required: true}
                        }
                    },
                ]],
                view: detailview,
                //rowIndex:行的索引
                //rowData ：行数据
                detailFormatter: function (rowIndex, rowData) {
                    return '<table><tr>' +
                        '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/lun/' + rowData.imgpath + '" style="height:50px;"></td>' +
                        '<td style="border:0">' +
                        '<p>描述: ' + rowData.title + '</p>' +
                        '<p>Status: ' + rowData.status + '</p>' +
                        '</td>' +
                        '</tr></table>';
                }
            });

        })

        function doInsert() {
            $("#insertDiv").val("")
            $("#insertForm").form("submit", {
                url: "banner/insert",
                onSubmit: function () {
                    var isOk = $("#insertForm").form("validate");
                    return isOk;
                },
                success: function (data) {
                    data = JSON.parse(data);
                    if (data.insert) {
                        $("#insertDiv").dialog("close");
                        $("#myDatagrid").datagrid("load");
                    } else {
                        alert("添加失败,请确认");
                    }
                }
            })
        }

        function toOpenUpdateDialog(rowData) {
            //打开修改对话框
            $("#updateDiv").dialog("open");
            $("#title").val(rowData.title);
            $("#id").val(rowData.id);
            $("#status").val(rowData.status);
            $("#file").val(rowData.file);
            $("#updateDiv").val("");
        }

        function doUpdate() {
            $("#updateForm").form("submit", {
                url: "banner/update1",
                success: function (data) {
                    data = JSON.parse(data);
                    if (data.update) {
                        //关闭修改对话框
                        $("#updateDiv").dialog("close");
                        //刷新datagrid
                        $("#myDatagrid").datagrid("load");
                    } else {
                        alert("修改失败，请确认")
                    }
                }
            })
        }

    </script>
</head>
<body>


<table id="myDatagrid"></table>
<div id="updateDiv">
    <form id="updateForm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" id="id"/>
        图名：<input name="title" id="title"/><br/>
        状态：<input name="status" id="status"/><br/>
        图片：<input type="file" name="file" id="file">
        <br/>
    </form>
</div>

<%--修改对话框对应的HTML代码===END===--%>

<%--修改对话框底部的提交按钮--%>
<div id="updateBtn">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="doUpdate()">提交</a>
</div>

<div id="insertDiv">
    <form id="insertForm" method="post" enctype="multipart/form-data">
        图名：<input name="title"/><br/>
        图片：<input type="file" name="file"/><br/>
        <br/>
    </form>
</div>

<div id="insertBtn">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="doInsert()">提交</a>
</div>

</body>
</html>
