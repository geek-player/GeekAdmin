<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2023/1/1
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Geek Admin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" type="text/css" href="../style/geek.css">
        <link rel="stylesheet" type="text/css" href="../style/toast.css">
        <link rel="stylesheet" type="text/css" href="../style/code.css">
        <link rel="stylesheet" type="text/css" href="../style/nprogress.css">
        <link rel="shortcut icon" href="favicon.ico">
    </head>
    <body>
        <div class="geek-main geek-flex">

            <div class="geek-card row">
                <h4>请填写数据库连接</h4>

                <table class="code-table">
                    <tbody>
                    <tr>
                        <td>用户名</td>
                        <td>
                            <div class="geek-form-input">
                                <img class="geek-input-logo" src="../images/user.svg" alt="username"/>
                                <input class="geek-input" type="text" id="username" placeholder="用户名" value="root">
                            </div>
                        </td>
                        <td>密码</td>
                        <td>
                            <div class="geek-form-input">
                                <img class="geek-input-logo" src="../images/password.svg" alt="password"/>
                                <input class="geek-input" type="password" id="password" placeholder="密码" value="root">
                            </div>
                        </td>
                        <td>IP地址</td>
                        <td>
                            <div class="geek-form-input">
                                <img class="geek-input-logo" src="../images/ip.svg" alt="ip"/>
                                <input class="geek-input center" type="text" id="ip0" oninput="if(value.length>3)value=value.slice(0,3)" value="127">
                                <span>.</span>
                                <input class="geek-input center" type="text" id="ip1" oninput="if(value.length>3)value=value.slice(0,3)" value="0">
                                <span>.</span>
                                <input class="geek-input center" type="text" id="ip2" oninput="if(value.length>3)value=value.slice(0,3)" value="0">
                                <span>.</span>
                                <input class="geek-input center" type="text" id="ip3" oninput="if(value.length>3)value=value.slice(0,3)" value="1">
                            </div>
                        </td>
                        <td>数据库版本</td>
                        <td>
                            <div class="geek-form-input">
                                <img class="geek-input-logo" src="../images/database.svg" alt="password"/>
                                <select class="geek-select" id="version">
                                    <option>请选择您的数据库版本</option>
                                    <option value="5">5.x</option>
                                    <option value="8" selected>8.x</option>
                                </select>
                            </div>
                        </td>
                        <td>
                            <button class="geek-button-green" id="connect">连接</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <ul class="notifications"></ul>
    </body>
    <script type="text/javascript" src="../script/jquery-3.6.3.min.js"></script>
    <script type="text/javascript" src="../script/toast.js"></script>
    <script type="text/javascript" src="../script/code.js"></script>
    <script type="text/javascript" src="../script/nprogress.js"></script>
    <script type="text/javascript">
        NProgress.start();
        NProgress.done();
    </script>
</html>