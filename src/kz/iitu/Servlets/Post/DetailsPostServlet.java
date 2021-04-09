package kz.iitu.Servlets.Post;

import kz.iitu.DB.DBManager;
import kz.iitu.DB.Post;
import kz.iitu.DB.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/detailsPost")
public class DetailsPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        if (user!= null) {
            Long id = Long.valueOf(request.getParameter("id"));
            Post post = DBManager.getPostById(id);
            request.setAttribute("post", post);
            request.setAttribute("user", user);
            request.getRequestDispatcher("detailsPost.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
