package cc.geekadmin.servlet;

import cc.geekadmin.server.Server;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @Author Admin
 * @create 2023/1/13 14:50
 */
@WebServlet(name = "ServerServlet", value = "/ServerServlet")
public class ServerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Server server = new Server();
        server.copyTo();
        request.setAttribute("server",server);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
