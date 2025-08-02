package dao;
import model.Account;
import java.sql.*;
import util.DBConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    public static boolean create_account(Account acc){
        try{
            Connection conn=DBConnection.getConnection();
            String sql="INSERT INTO ACCOUNTS(USER_ID,ACCOUNT_NUMBER,BALANCE) VALUES (?,?,?) ";
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setInt(1,acc.getUserid());
            pre.setString(2,acc.getAccount_number());
            pre.setDouble(3,acc.getBalance());

            return pre.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;


    }

    public static List<Account> getUserAccounts(int userId){
        List<Account> list=new ArrayList<>();
        try{
            Connection conn=DBConnection.getConnection();
            String sql="SELECT * FROM ACCOUNTS WHERE USER_ID =?";
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setInt(1, userId);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Account acc=new Account();
                acc.setId(rs.getInt("id"));
                acc.setUserid(rs.getInt("user_id"));
                acc.setAccount_number(rs.getString("account_number"));
                acc.setBalance(rs.getDouble("balance"));
                list.add(acc);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;

    }


}
























