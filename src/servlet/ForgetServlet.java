package servlet;

import com.google.gson.Gson;
import dao.UserDao;
import entity.User;
import utils.MailUtils;
import utils.SecureUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "forget",urlPatterns = "/Forget")
public class ForgetServlet extends HttpServlet {

    private byte[] emailKEY = "yfHyN!5nyvtfMs9nF1P2QRkx".getBytes();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String name=request.getParameter("username");
        String email=request.getParameter("email");
        Map<String, Object> res = new HashMap<>();
        String error=null;
        if ("".equals(email)) {
            error="邮箱不能为空";
        }
       else {
            User user= null;
            try {
                user = UserDao.getByName(name);
                System.out.println(user);
                if (user == null) {
                    error="该用户不存在";
                }else
                {
                    String emails=user.getEmail();
                    if(!emails.equals(email)){
                         error="邮箱不正确";
                    }else{
                        //计算token
                        String token=String.format("%s,%s,%s,%s",user.getUid(),user.getUsername(),user.getEmail(),System.currentTimeMillis());
                        //加密
                        token = SecureUtils._3DES_encode(emailKEY, token.getBytes());
                        //计算url
                        String url=request.getRequestURL().toString();
                        url=url.replace(request.getRequestURI(),"");
                        url=url+request.getContextPath()+"/Reset?token="+token;
                        //发送邮件
                        System.out.println(email+" "+token);
                        MailUtils.sendMail(user.getEmail(),"重置链接："+url);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        res.put("mess",error);
        System.out.println("返回信息"+error);
        response.getWriter().write(new Gson().toJson(res));
    }
}
