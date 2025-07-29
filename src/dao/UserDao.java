package dao;
import util.DBConnection;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    //DAO---->Document Access Object
    public static boolean registerUser(User user){
        boolean status=false;
        try{
            Connection conn= DBConnection.getConnection();
            String sql="INSERT INTO USERS (NAME,USERNAME,PASSWORD) VALUES (?,?,?)";
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setString(1,User.getName());
            pre.setString(2,User.getUsername());
            pre.setString(3,User.getPassword());

            int rows=pre.executeUpdate();
            if(rows>0){
                status=true;
            }


        }catch(Exception e){
          e.printStackTrace();
        }
        return status;
    }
    public static boolean isUserNameTaken(String username){
        try{
            Connection conn=DBConnection.getConnection();
            String sql="SELECT * FROM USERS WHERE USERNAME=?";
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setString(1,User.getUsername());
            ResultSet rs=pre.executeQuery();
            return  rs.next();
        }catch(Exception e){
            e.printStackTrace();
            return true;
        }
    }
    public static User Login(String username,String password){
        User user=null;
        try{
            Connection conn=DBConnection.getConnection();
            String sql="SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setString(1,username);
            pre.setString(2,password);
            ResultSet rs=pre.executeQuery();

            if(rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return user;


    }

}





















