<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2023/1/1
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Geek Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="style/geek.css">
    <link rel="stylesheet" type="text/css" href="style/toast.css">
    <link rel="stylesheet" type="text/css" href="style/nprogress.css">
    <link rel="shortcut icon" href="favicon.ico">
</head>
<body>
<div class="geek-main geek-card geek-flex">
    <div id="geek-navigation" class="geek-navigation open geek-card">
        <div class="geek-navigation-top geek-flex">
            <img class="geek-icon geek-navigation-logo" src="images/geek-admin.png"/>
            <span>Geek Admin</span>
            <img id="geek-navigation-fold" class="geek-icon" src="images/close.png" style="margin-left: auto;"/>
        </div>
        <div class="geek-navigation-list">
            <ul>
                <li class="geek-flex geek-item current">
                    <img class="geek-icon" src="images/console_current.png" alt="console"><span>控制台</span>
                </li>
                <li class="geek-flex geek-item">
                    <img class="geek-icon" src="images/system.png" alt="system"><span>系统管理</span>
                </li>
                <li class="geek-flex geek-item">
                    <img class="geek-icon" src="images/project.png" alt="project"><span>项目管理</span>
                </li>
                <li class="geek-flex geek-item">
                    <img class="geek-icon" src="images/code.png" alt="code"><span>代码生成</span>
                </li>
                <li class="geek-flex geek-item">
                    <img class="geek-icon" src="images/setting.png" alt="setting"><span>系统设置</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="geek-content geek-flex">
        <div class="geek-top geek-flex">
            <div class="geek-tabs geek-card geek-flex">
                <div class="buttons">
                    <button class="geek-button-green" id="success">Success</button>
                    <button class="geek-button-red" id="error">Error</button>
                    <button class="geek-button-yellow" id="warning">Warning</button>
                    <button class="geek-button-blue" id="info">Info</button>
                </div>
            </div>
            <div class="geek-menus geek-card geek-flex"></div>
        </div>
        <div class="geek-page geek-card geek-flex">
            <iframe id="geek-page" src="page/console.jsp" style="width: 100%; height: 100%; border: 0;"></iframe>
        </div>
    </div>
</div>
<ul class="notifications"></ul>
</body>
<script type="text/javascript" src="script/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="script/geek.js"></script>
<script type="text/javascript" src="script/toast.js"></script>
<script type="text/javascript" src="script/nprogress.js"></script>
<script type="text/javascript">
    NProgress.start();
    NProgress.done();
</script>
</html>
