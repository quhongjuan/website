package dao;

import entity.Topic;
import utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDao {
        public static Integer add(Topic t)throws SQLException{
                PreparedStatement prep;
                String sql="INSERT INTO topic(`ptext`,`pauthor`,`ptime`) VALUE(?,?,?)";
                Connection connection= DBUtils.getConnection();
                prep=connection.prepareStatement(sql);
                prep.setString(1,t.getpText());
                prep.setString(2,t.getpAuthor());
                prep.setTimestamp(3, (Timestamp) t.getpTime());
                prep.executeUpdate();
                DBUtils.release(prep);
                prep=connection.prepareStatement("SELECT MAX(pid) as p from topic");
                ResultSet rs=prep.executeQuery();
                if(rs.next()){
                        return rs.getInt("p");
                }
                return null;
        }
        public static List<Topic> getByPid(int id) throws SQLException{
                Connection connection=DBUtils.getConnection();
                PreparedStatement preparedStatement;
                String sql="SELECT * FROM topic WHERE pid=?";
                List<Topic> topics=new ArrayList<Topic>();
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setInt(1,id);
                ResultSet rs=preparedStatement.executeQuery();
                if(rs.next()){
                        Topic topic=new Topic();
                        topic.setPid(rs.getInt("pid"));
                        topic.setpText(rs.getString("ptext"));
                        topic.setpAuthor((rs.getString("pauthor")));
                        topic.setpTime(rs.getTimestamp("ptime"));
                        topic.setpCount(rs.getInt("pcount"));
                        topics.add(topic);
                }
                return  topics;
        }


        //查看全部topic
        public static List<Topic> getAllTopic() throws SQLException{
                Connection connection=DBUtils.getConnection();
                PreparedStatement preparedStatement;
                String sql="SELECT topic.pid,topic.ptext,topic.pauthor,topic.ptext,cnt.pcount,topic.ptime FROM topic LEFT JOIN (SELECT  pid,count(1) as pcount  FROM Invitation GROUP BY pid) cnt on cnt.pid=topic.pid ORDER BY topic.pid DESC ";
                List<Topic> topics=new ArrayList<Topic>();
                preparedStatement=connection.prepareStatement(sql);
                ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()){
                        Topic topic=new Topic();
                        topic.setPid(rs.getInt("pid"));
                        topic.setpText(rs.getString("ptext"));
                        topic.setpAuthor((rs.getString("pauthor")));
                        topic.setpTime(rs.getTimestamp("ptime"));
                        topic.setpCount(rs.getInt("pcount"));
                        topics.add(topic);
                }
                return  topics;
        }
        //更新pcount
        /*
        public static void updateCount(int pid,int count) throws SQLException{
                Connection connection=DBUtils.getConnection();
                PreparedStatement preparedStatement;
                String sql="UPDATE topic set pcount=? WHERE pid=?";
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setInt(1,count);
                preparedStatement.setInt(2,pid);
                preparedStatement.executeUpdate();
                DBUtils.release(preparedStatement);
        }*/
}
