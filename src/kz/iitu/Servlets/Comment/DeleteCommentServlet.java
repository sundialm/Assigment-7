package kz.iitu.Servlets.Comment;

import kz.iitu.DB.DBManager;
import kz.iitu.DB.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deletePost")
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user!= null) {
            Long id = Long.valueOf(request.getParameter("delete_post_id"));
            request.setAttribute("user", user);
            if (DBManager.deleteComment(id)){
                response.sendRedirect("/userComments?success");
            }
            else {
                response.sendRedirect("/detailsComment?id=" + id+ "&error");
            }
        }
        else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
