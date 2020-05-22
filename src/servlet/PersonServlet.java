package servlet;

import dao.InvitationDao;
import dao.TopicDao;
import entity.Invitation;
import entity.Topic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name="Person",urlPatterns = "/person")
public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        //话题
        String author= (String) session.getAttribute("username");
        List<Invitation> invitationList=new ArrayList<Invitation>();

        try {
            invitationList=InvitationDao.getByAuthor(author);
            System.out.println("待查询作者"+author);
            session.setAttribute("list",invitationList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      request.getRequestDispatcher("person.jsp").forward(request,response);
    }
}
