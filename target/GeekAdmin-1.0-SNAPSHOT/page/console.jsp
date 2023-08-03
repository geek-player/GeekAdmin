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
        <link rel="stylesheet" type="text/css" href="../style/nprogress.css">
        <link rel="shortcut icon" href="favicon.ico">
    </head>

    <body>
    <c:import url="/ServerServlet"/>
        <div class="geek-main geek-flex">
            <div class="geek-card half">
                <h4>CPU</h4>
                <table class="console-table">
                    <thead>
                        <tr>
                            <th>属性</th>
                            <th>值</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>核心数</td>
                            <td>${server.getCpu().getCpuNum()}个</td>
                        </tr>
                        <tr>
                            <td>用户使用率</td>
                            <td>${server.getCpu().getUsed()}%</td>
                        </tr>
                        <tr>
                            <td>系统使用率</td>
                            <td>${server.getCpu().getSys()}%</td>
                        </tr>
                        <tr>
                            <td>当前空闲率</td>
                            <td>${server.getCpu().getFree()}%</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="geek-card half">
                <h4>内存</h4>
                <table class="console-table">
                    <thead>
                        <tr>
                            <th>属性</th>
                            <th>内存</th>
                            <th>JVM</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>总内存</td>
                            <td>${server.getMem().getTotal()}G</td>
                            <td>${server.getJvm().getTotal()}M</td>
                        </tr>
                        <tr>
                            <td>已用内存</td>
                            <td>${server.getMem().getUsed()}G</td>
                            <td>${server.getJvm().getUsed()}M</td>
                        </tr>
                        <tr>
                            <td>剩余内存</td>
                            <td>${server.getMem().getFree()}G</td>
                            <td>${server.getJvm().getFree()}M</td>
                        </tr>
                        <tr>
                            <td>使用率</td>
                            <td ${server.getMem().getUsage() >= 80 ?'style="color: red"':''}>${server.getMem().getUsage()}%</td>
                            <td ${server.getJvm().getUsage() >= 80 ?'style="color: red"':''}>${server.getJvm().getUsage()}%</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="geek-card row">
                <h4>服务器信息</h4>
                <table class="console-table">
                    <tbody>
                        <tr>
                            <td>服务器名称</td>
                            <td>${server.getSys().computerName}</td>
                            <td>操作系统</td>
                            <td>${server.getSys().osName}</td>
                        </tr>
                        <tr>
                            <td>服务器IP</td>
                            <td>${server.getSys().computerIp}</td>
                            <td>系统架构</td>
                            <td>${server.getSys().osArch}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="geek-card row">
                <h4>Java虚拟机信息</h4>
                <table class="console-table">
                    <tbody>
                        <tr>
                            <td>Java名称</td>
                            <td>${server.getJvm().name}</td>
                            <td>Java版本</td>
                            <td>${server.getJvm().getVersion()}</td>
                        </tr>
                        <tr>
                            <td>启动时间</td>
                            <td>${server.getJvm().getStartTime()}</td>
                            <td>运行时长</td>
                            <td>${server.getJvm().getRunTime()}</td>
                        </tr>
                        <tr>
                            <td colspan="1">安装路径</td>
                            <td colspan="3">${server.getJvm().home}</td>
                        </tr>
                        <tr>
                            <td colspan="1">项目路径</td>
                            <td colspan="3">${server.getSys().userDir}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="geek-card row">
                <h4>磁盘状态</h4>
                <table class="console-table">
                    <thead>
                        <tr>
                            <th>盘符路径</th>
                            <th>文件系统</th>
                            <th>盘符类型</th>
                            <th>总大小</th>
                            <th>可用大小</th>
                            <th>已用大小</th>
                            <th>已用百分比</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${server.getSysFiles()}" var="sysFile">
                            <tr>
                                <td>${sysFile.dirName}</td>
                                <td>${sysFile.sysTypeName}</td>
                                <td>${sysFile.typeName}</td>
                                <td>${sysFile.total}</td>
                                <td>${sysFile.free}</td>
                                <td>${sysFile.used}</td>
                                <td ${sysFile.usage >= 80 ?'style="color: red"':''}>${sysFile.usage}%</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </body>

    <script type="text/javascript" src="../script/nprogress.js"></script>
    <script type="text/javascript">
        NProgress.start();
        NProgress.done();
    </script>

</html>