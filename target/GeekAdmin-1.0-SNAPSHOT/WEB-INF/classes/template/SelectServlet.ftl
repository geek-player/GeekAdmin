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

@WebServlet("/${className?cap_first}SelectServlet")
public class ${className?cap_first}SelectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        ${className?cap_first}Service ${className?uncap_first}Service = new ${className?cap_first}Service();

        int page = Integer.parseInt(!"".equals(request.getParameter("page")) && request.getParameter("page") != null ? request.getParameter("page") : "1");
        int limit = Integer.parseInt(!"".equals(request.getParameter("limit")) && request.getParameter("limit") != null ? request.getParameter("limit") : "10");
        int count = ${className?uncap_first}Service.select${className?cap_first}CountService();

        request.setAttribute("${className?uncap_first}List", ${className?uncap_first}Service.select${className?cap_first}ByPageService(page, limit));
        request.setAttribute("page", page);
        request.setAttribute("count", count);
        request.setAttribute("minPage", 1);
        request.setAttribute("maxPage", (count/limit)+(count%limit>0?1:0));
        request.setAttribute("limit", limit);

        request.getRequestDispatcher("${className?uncap_first}/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}