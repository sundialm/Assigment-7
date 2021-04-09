package kz.iitu.Servlets.Post;

import kz.iitu.DB.Comment;
import kz.iitu.DB.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addComment")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long author_id  = Long.valueOf(request.getParameter("author_id"));
        String content = request.getParameter("content");
        if (DBManager.addComment(new Comment(null, DBManager.getUserById(author_id), content, null))){
            response.sendRedirect("/userComments?success");
        }
        else response.sendRedirect("/userPosts?error");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
