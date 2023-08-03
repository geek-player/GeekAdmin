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

@WebServlet("/${className?cap_first}CourseEditServlet")
public class ${className?cap_first}EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        ${className?cap_first}Service ${className?uncap_first}Service = new ${className?cap_first}Service();

        ${columns[0].columnClassName?replace("Integer", "int")} id = <#if columns[0].columnClassName == "Integer">Integer.parseInt(</#if>request.getParameter("id")!=null&&!"".equals(request.getParameter("id"))?request.getParameter("id"):"0"<#if columns[0].columnClassName == "Integer">)</#if>;

        request.setAttribute("${className?uncap_first}",${className?uncap_first}Service.select${className?cap_first}ByIdService(id));

        request.getRequestDispatcher("${className?uncap_first}/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
