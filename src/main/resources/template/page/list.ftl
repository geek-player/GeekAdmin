<%--
  Created by IntelliJ IDEA.
  User: ${SystemUser}
  Date: ${DateTime?date}
  Time: ${DateTime?time}
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${tableRemarksName}列表</title>
</head>
<body>
<div sty1le="margin: auto; text-align: center; padding-top: 100px">
    <table>
        <tr>
            <#list columns as column>
            <th>${column.columnRemarksName}</th>
            </#list>
            <th colspan="2">${tableRemarksName}操作</th>
        </tr>
        <c:forEach items="${r"${"}${tableName}${r"List}"}" var="${tableName}">
            <tr>
                <#list columns as column>
                <th>${r"${"}${tableName}.${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}${r"}"}</th>
                </#list>
                <td><a href="${tableName?cap_first}Servlet?method=operation&type=update&${columns[0].columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}=${r"${"}${tableName}.${columns[0].columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}${r"}"}"><button type="button">修改</button></a></td>
                <td><a href="${tableName?cap_first}Servlet?method=operation&type=delete&${columns[0].columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}=${r"${"}${tableName}.${columns[0].columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}${r"}"}"><button type="button">删除</button></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>