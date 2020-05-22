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

@WebServlet(name = "pwInvitation", urlPatterns = "/pwInvitation")
public class pwInvitation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int pid = Integer.parseInt(request.getParameter("pid"));


        List<Invitation> invitations = new ArrayList<Invitation>();
        try {
            invitations = InvitationDao.getByPid(pid);
            //获取标题
            List<Topic> t=new ArrayList<Topic>();
            t= TopicDao.getByPid(pid);
            Topic t1=new Topic();
            t1=t.get(0);
            String topictext=t1.getpText();
            request.setAttribute("title",topictext);
            /*
            for (Invitation inv : invitations)
                System.out.println(invitations);
              */
            request.setAttribute("pid", pid);
            request.setAttribute("invitations", invitations);
            request.getRequestDispatcher("/WatchInvitation.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int pid = Integer.parseInt(request.getParameter("pid"));

        String invitationText = request.getParameter("editordata");
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        try {
            Invitation invitation1 = new Invitation(pid, username, invitationText, time);
            InvitationDao.add(invitation1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/pwInvitation?pid="+pid);
    }
}

