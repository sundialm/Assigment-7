package kz.iitu.Servlets.SettingServlets;

import kz.iitu.DB.DBManager;
import kz.iitu.DB.Post;
import kz.iitu.DB.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/userPosts")
public class UserPostsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        if (user!= null){
            ArrayList<Post> posts = DBManager.getPostsByUserId(user.getId());
            request.setAttribute("posts", posts);
            request.setAttribute("user", user);
            request.getRequestDispatcher("userPosts.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
