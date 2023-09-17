package cc.geekadmin.servlet;

import cc.geekadmin.entity.Database;
import cc.geekadmin.utils.TemplateUtils;
import cc.geekadmin.utils.TestDatabaseUtils;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author Admin
 * @create 2023/1/11 22:31
 */
@WebServlet(name = "CodeServlet", value = "/CodeServlet")
public class CodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ip = request.getParameter("ip");
        String databaseName = "test";//只需把表复制到 test 数据库里面创建表就可以
        String url = "jdbc:mysql://" + ip + ":3306/" + databaseName + "?characterEncoding=utf8&useInformationSchema=true";
        String driver = ("5".equals(request.getParameter("version")) ? "com.mysql.jdbc.Driver" : "com.mysql.cj.jdbc.Driver");
        String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TestDatabaseUtils testDatabaseUtils = new TestDatabaseUtils();
        Database database = testDatabaseUtils.getDatabase(driver, url, username, password);
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("data", JSONObject.toJSON(database));
        json.put("zip", fileName);
        response.getWriter().println(json);
        TemplateUtils.generate(System.getProperty("catalina.home") + File.separator + "webapps" + request.getServletContext().getContextPath() + File.separator, database, fileName);
        TemplateUtils.zipGenerate(fileName, System.getProperty("catalina.home") + File.separator + "webapps" + request.getServletContext().getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
