package utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
    public static final String HOST ="smtp.likole.com";//邮件服务器
    public static final String USERNAME ="qhj@likole.com";
    public static final String PASSWORD ="Qhj12345";

    public static boolean sendMail(String to, String content) {

        Properties props = new Properties();
        props.put("mail.smtp.HOST", HOST);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        // 环境信息
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        // 在session中设置账户信息，Transport发送邮件时会使用
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            //邮件
            MimeMessage msg = new MimeMessage(session);
            //发件人
            msg.setFrom(new InternetAddress(USERNAME));
            //设置收件人
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to));
            msg.setSubject("重置密码");
            //整封邮件的MIME消息体
            Multipart mp = new MimeMultipart();
            MimeBodyPart mbpContent = new MimeBodyPart();//附件
            mbpContent.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(mbpContent);
            msg.setContent(mp);
            msg.setSentDate(new Date());
            msg.saveChanges();

            //send
            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, USERNAME, PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }

}
