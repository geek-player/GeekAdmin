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
  <title>Login</title>
  <link rel="stylesheet" type="text/css" href="style/geek.css">
  <link rel="stylesheet" type="text/css" href="style/login.css">
  <link rel="stylesheet" type="text/css" href="style/toast.css">
  <link rel="shortcut icon" href="favicon.ico">
</head>
<body>
<div class="geek-card geek-flex login-card">
  <img class="login-left-img" src="images/login_left.png" alt="login">
  <div class="geek-card login-form">
    <div class="geek-flex login-logo">
      <img src="images/geek-admin.png" alt="logo">
      <h2>Geek Admin</h2>
    </div>
    <form class="geek-form">
      <div class="geek-form-input login-username">
        <img class="geek-input-logo" src="images/user.svg" alt="username"/>
        <input class="geek-input" type="text" placeholder="用户名：admin / user">
      </div>
      <div class="geek-form-input login-password">
        <img class="geek-input-logo" src="images/password.svg" alt="password"/>
        <input class="geek-input" type="password" placeholder="密码：123456">
      </div>
    </form>
    <div class="geek-flex login-button">
      <button id="reset" class="geek-button">重置</button>
      <button id="login" class="geek-button-green">登录</button>
    </div>
  </div>
</div>
<ul class="notifications"></ul>
</body>
<script type="text/javascript" src="script/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="script/geek.js"></script>
<script type="text/javascript" src="script/login.js"></script>
<script type="text/javascript" src="script/toast.js"></script>
</html>