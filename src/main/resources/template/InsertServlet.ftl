package ${classPath}.servlet;

import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ${classPath}.model.${className?cap_first};
import ${classPath}.service.${className?cap_first}Service;

@WebServlet("/${className?cap_first}InsertServlet")
public class ${className?cap_first}InsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        ${className?cap_first}Service ${className?uncap_first}Service = new ${className?cap_first}Service();

        <#list columns as column>
        <#if column_index == 0><#continue></#if>
        <#if column.columnClassName == "Date">
        ${column.columnClassName} ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first} = new Date();
        <#elseif column.columnClassName == "Integer">
        ${column.columnClassName} ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first} = Integer.valueOf(request.getParameter("${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}"));
        <#elseif column.columnClassName == "Double">
        ${column.columnClassName} ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first} = Double.valueOf(request.getParameter("${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}"));
        <#else>
        ${column.columnClassName} ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first} = request.getParameter("${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}");
        </#if>
        </#list>

        ${className?cap_first} ${className?uncap_first} = new ${className?cap_first}(null<#list columns as column><#if column?index == 0><#continue></#if><#if column?index gt 0>,</#if>${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}</#list>);

        if ( ${className?uncap_first}Service.insert${className?cap_first}Service(${className?uncap_first}) ) {
            response.getWriter().println("Yes");
        } else {
            response.getWriter().println("NO");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}