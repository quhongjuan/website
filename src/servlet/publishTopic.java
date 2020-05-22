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
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "publishTopic",urlPatterns = "/publishTopic")
public class publishTopic extends HttpServlet {
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
        String topicText=request.getParameter("topic");
        String author= (String) session.getAttribute("username");
        Date date=new Date();
        Timestamp time=new Timestamp(date.getTime());
        int count= 1;
        int pid=0;
        Topic topic=new Topic(topicText,author,time,count);
        try {
            pid=TopicDao.add(topic);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //回帖
        String invitationText=request.getParameter("textContext");
        try {
            Invitation invitation=new Invitation(pid,author,invitationText,time);
            InvitationDao.add(invitation);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/forum");

    }
}
