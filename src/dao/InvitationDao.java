package dao;

import entity.Invitation;
import utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvitationDao {
    public static Integer add(Invitation invitation)throws SQLException {
        PreparedStatement prep;
        String sql="INSERT INTO Invitation(`pid`,`tauthor`,`ttext`,`ttime`) VALUE(?,?,?,?)";
        Connection connection= DBUtils.getConnection();
        prep=connection.prepareStatement(sql);
        prep.setInt(1,invitation.getpId());
        prep.setString(2,invitation.gettAuthor());
        prep.setString(3,invitation.gettText());
        prep.setTimestamp(4,invitation.gettTime());
        prep.executeUpdate();
        DBUtils.release(prep);
        prep=connection.prepareStatement("select max(tid) as t FROM  `Invitation`");
        ResultSet resultset= prep.executeQuery();
        if(resultset.next()){
            return resultset.getInt("t");
        }
        return null;
    }


    public static List<Invitation> getByPid(int pid) throws SQLException{
        Connection connection=DBUtils.getConnection();
        PreparedStatement preparedStatement;
        List<Invitation> invitations=new ArrayList<Invitation>();
        String sql="SELECT * FROM Invitation where pid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,pid);
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id1=rs.getInt("pid");
            int id2=rs.getInt("tid");
            String author= rs.getString("tauthor");
            String text=rs.getString("ttext");
            Timestamp time=rs.getTimestamp("ttime");
            Invitation invi=new Invitation(id2,id1,author,text,time);
            invitations.add(invi);
        }
        return invitations;
    }

//通过某人的名字查找帖子
    public static List<Invitation> getByAuthor(String author) throws SQLException{
        Connection connection=DBUtils.getConnection();
        PreparedStatement preparedStatement;
        List<Invitation> invitations=new ArrayList<Invitation>();
        String sql="SELECT * FROM Invitation left join topic on topic.pid =Invitation.pid where tauthor=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,author);
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id1=rs.getInt("pid");
            int id2=rs.getInt("tid");
            String author1= rs.getString("tauthor");
            String text=rs.getString("ttext");
            Timestamp time=rs.getTimestamp("ttime");
            Invitation invi=new Invitation(id2,id1,author1,text,time);
            invi.setpText(rs.getString("ptext"));
            invitations.add(invi);
        }
        return invitations;
    }
    //删除帖子
    public static void deleteByTid(int id) throws SQLException{
        Connection connection=DBUtils.getConnection();
        PreparedStatement preparedStatement;
        String sql="DELETE  FROM Invitation WHERE tid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        DBUtils.release(preparedStatement);
    }
}
