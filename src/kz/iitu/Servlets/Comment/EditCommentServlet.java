package kz.iitu.Servlets.Comment;

import kz.iitu.DB.Comment;
import kz.iitu.DB.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editPost")
public class EditCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long post_id  = Long.valueOf(request.getParameter("update_comment_id"));
        String content = request.getParameter("update_content");
        if (DBManager.updateComment(new Comment(post_id, null, content, null))){
            response.sendRedirect("/detailsComment?id="+post_id+"&success");
        }
        else response.sendRedirect("/detailsComment?id="+post_id+"&error");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
