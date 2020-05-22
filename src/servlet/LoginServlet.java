package servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import entity.User;
import com.google.gson.Gson;
import utils.DBUtils;

@WebServlet(name="LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String formFalg=request.getParameter("flag");
        Map<String, Object> res = new HashMap<>();
        String message=null;
        Connection conn=null;
        try {
            conn= DBUtils.getConnection();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("连接失败");
        }


        if(formFalg.equals("login")){
            String username=request.getParameter("lname");
            String password=request.getParameter("lpassword");
            try {
                User u=new User();
                u=UserDao.getByName(username);
                if(u!=null){
                    if(u.getPassword().equals(password))
                    {
                        message="登陆成功";
                        HttpSession session=request.getSession();
                        session.setAttribute("username",username);
                    }
                    else message="密码错误";
                }else message="还未注册，请先注册";

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(formFalg.equals("register")){
            String username = request.getParameter("rname");
            String email = request.getParameter("remail");
            String password = request.getParameter("rpassword");

            try {
                if(UserDao.getByName(username)==null){
                    User user=new User();
                    user.setEmail(email);
                    user.setUsername(username);
                    user.setPassword(password);
                    System.out.println(user);
                    UserDao.add(user);
                    message="注册成功";
                }
                else message="该用户名已被使用，请更换！";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        res.put("mess",message);
        response.getWriter().write(new Gson().toJson(res));
    }
}
