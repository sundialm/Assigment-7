package kz.iitu.Servlets.Post;

import kz.iitu.DB.DBManager;
import kz.iitu.DB.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addPost")
public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long author_id  = Long.valueOf(request.getParameter("author_id"));
        String title = request.getParameter("title");
        String short_content = request.getParameter("short_content");
        String content = request.getParameter("content");
        if (DBManager.addPost(new Post(null, DBManager.getUserById(author_id), title , short_content, content, null))){
            response.sendRedirect("/userPosts?success");
        }
        else response.sendRedirect("/userPosts?error");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
