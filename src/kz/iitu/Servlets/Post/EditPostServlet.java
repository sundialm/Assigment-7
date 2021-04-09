package kz.iitu.Servlets.Post;

import kz.iitu.DB.DBManager;
import kz.iitu.DB.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editPost")
public class EditPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long post_id  = Long.valueOf(request.getParameter("update_post_id"));
        String title = request.getParameter("update_title");
        String short_content = request.getParameter("update_short_content");
        String content = request.getParameter("update_content");
        if (DBManager.updatePost(new Post(post_id, null, title , short_content, content, null))){
            response.sendRedirect("/detailsPost?id="+post_id+"&success");
        }
        else response.sendRedirect("/detailsPost?id="+post_id+"&error");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
