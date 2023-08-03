package cc.geekadmin.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @Author Admin
 * @create 2023/8/2 0:46
 */
@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();

        //获取客户端传来的json
        BufferedReader reader = request.getReader();
        JSONObject json = JSONObject.parseObject(reader.readLine());
        reader.close();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ip = request.getParameter("ip");
        int version = Integer.parseInt(request.getParameter("version"));

        String url = "jdbc:mysql://" + ip + ":3306/mysql?characterEncoding=utf8&useInformationSchema=true";

        System.out.println(url);
        try {
            Class.forName(version == 5 ?"com.mysql.jdbc.Driver":"com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement("SHOW TABLES");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
