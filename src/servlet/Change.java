package servlet;

import com.google.gson.Gson;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="Change",urlPatterns = "/Change")
public class Change extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String name =request.getParameter("username");
        String newPass=request.getParameter("npassword");
        String oldPass=request.getParameter("opassword");
        Map<String,Object> rs=new HashMap<>();
        User user=new User();
        String mess=null;
        try {
            user= UserDao.getByName(name);
            if(user==null){
                mess="该用户名不存在";
            }else{
                String p=user.getPassword();
                if(p.equals(oldPass)){
                    UserDao.updatePassword(name,newPass);
                    mess="修改成功！";
                }else{
                    mess="用户名或原密码不正确！";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        rs.put("mess",mess);
        //response.getWriter().write(new Gson().toJson(rs));
        response.getWriter().write(new Gson().toJson(rs));
    }
}
