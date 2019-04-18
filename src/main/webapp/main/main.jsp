<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            //中间部分的tabs
            $("#tt").tabs({
                fit: true
            })
        });

        function aaa(title, pjsp) {
            var isExists = $('#tt').tabs("exists", title);
            if (isExists) {
                $('#tt').tabs("select", title);
            } else {
                $('#tt').tabs("add", {
                    title: title,
                    closable: true,
                    selected: true,
                    content: '<iframe src="${pageContext.request.contextPath}/' + pjsp + '" width="100%" height="100%"></iframe>'
                });
            }
        }
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${sessionScope.name} &nbsp;
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn

    </div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">
        <c:forEach var="a" items="${sessionScope.list}">

            <div data-options="title:'${a.title}',iconCls:'${a.icon_cls}'">
                <c:forEach var="b" items="${a.childMenus}">

                    <div align="center">
                        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'${b.picon_cls}'"
                           onclick="aaa('${b.ptitle}','${b.pjsp_url}')">${b.ptitle}</a>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
</div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>