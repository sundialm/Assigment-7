package kz.iitu.Servlets.SettingServlets;

import kz.iitu.DB.DBManager;
import kz.iitu.DB.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class AuthenticationUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = DBManager.getUserByEmailPassword(email, password);
        if (user != null){
            request.getSession().setAttribute("user", user);;
            response.sendRedirect("/feed");
        }
        else {
//            request.getRequestDispatcher("login.jsp").forward(request, response);

            response.sendRedirect("/login?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
