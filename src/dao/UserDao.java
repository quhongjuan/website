package dao;

import entity.User;
import utils.DBUtils;

import java.sql.*;

public class UserDao {

    public static void add(User u) throws SQLException {
        PreparedStatement prep;
        String sql = "INSERT INTO usertable(`name`,`password`,`email`) VALUE(?,?,?)";
        Connection connection = DBUtils.getConnection();
        prep = connection.prepareStatement(sql);
        prep.setString(1, u.getUsername());
        prep.setString(2, u.getPassword());
        prep.setString(3, u.getEmail());
        prep.executeUpdate();
        DBUtils.release(prep);

    }

    public static User getByName(String name) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement ;
        String sql = "SELECT * FROM usertable where name=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet rs = preparedStatement.executeQuery();
        User u=new User();
        if(rs.next()){
            u.setUid(rs.getInt("id")); ;
            u.setUsername(rs.getString("name"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            return u;
        }
        return null;
    }
      public static void  updatePassword(String name,String password){
              Connection connection=DBUtils.getConnection();
              PreparedStatement preparedStatement;
              String sql="UPDATE usertable set password=? WHERE name=?";
          try {
              preparedStatement=connection.prepareStatement(sql);
              preparedStatement.setString(1,password);
              preparedStatement.setString(2,name);
              preparedStatement.executeUpdate();
              DBUtils.release(preparedStatement);
          } catch (SQLException e) {
              e.printStackTrace();
          }

      }

}
