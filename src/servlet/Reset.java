package servlet;

import dao.UserDao;
import entity.User;
import utils.SecureUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Reset",urlPatterns = "/Reset")
public class Reset extends HttpServlet{
    private byte[] emailKEY = "yfHyN!5nyvtfMs9nF1P2QRkx".getBytes();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String token=request.getParameter("token");
        PrintWriter out=response.getWriter();
        if("".equals(token)){
            out.println("请不要直接访问这个链接！！！");
        }
        if (token.length() < 10) {
            out.println("非法token");
        }
        try {
            //解密
            token = SecureUtils._3DES_decode(emailKEY, SecureUtils.hexstr2bytearray(token));
            if (token == null) {
                out.println("非法token");
            }
            String[] tmp = token.split(",", 4);
            if (tmp.length != 4 || tmp[0].length() == 0 || tmp[1].length() == 0 || tmp[2].length() == 0||tmp[3].length()==0) {
                out.println("非法token");
            }
            long time = Long.parseLong(tmp[3]);
            if (System.currentTimeMillis() - time > 10*60*1000) {
                out.println("该重置链接已经超时");
            }
            String uname=tmp[1];
            User user= UserDao.getByName(uname);
            if(user==null) {
                out.println("用户不存在");
            }
            if(tmp[1].equals(user.getUsername())&&tmp[2].equals(user.getEmail())){
                String password=SecureUtils.getStringRandom(6);
                UserDao.updatePassword(uname,password);
               out.println("你的新密码是："+password);
            }else{
                out.println("验证失败!!请重新验证!!");
            }
        } catch (Throwable e) {
            out.println("非法token");
        }
    }
}
