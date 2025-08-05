package dao;

import model.Transaction;
import util.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    public static boolean addTransaction(Transaction t){
        try {
            Connection conn= DBConnection.getConnection();
            String sql="INSERT INTO TRANSACTIONS (ACCOUNT_ID,TYPE,AMOUNT) VALUES (?,?,?)";
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setInt(1,t.getAccountId());
            pre.setString(2,t.getType());
            pre.setDouble(3,t.getAmount());
            int rows=pre.executeUpdate();
            if(rows>0){
                String updateSql=t.getType().equalsIgnoreCase("DEPOSIT")?
                        "UPDATE ACCOUNTS SET BALANCE =BALANCE + ? WHERE ID =?" :
                        "UPDATE ACCOUNTS SET BALANCE =BALANCE - ? WHERE ID=?";
                PreparedStatement preup=conn.prepareStatement(updateSql);
                preup.setDouble(1,t.getAmount());
                preup.setInt(2,t.getAccountId());
                return preup.executeUpdate()>0;
            }
        }catch (Exception e){
              e.printStackTrace();
        }
        return false;
    }

    public static List<Transaction> getTransactionByAccount(int accId){
        List<Transaction> list=new ArrayList<>();
        try{
            Connection conn=DBConnection.getConnection();
            String sql="SELECT * FROM TRANSACTIONS WHERE ACCOUNT_ID=? ORDER BY DATE DESC";
            PreparedStatement pre= conn.prepareStatement(sql);
            pre.setInt(1,accId);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Transaction t=new Transaction();
                t.setId(rs.getInt("id"));
                t.setAccountId(rs.getInt("accountId"));
                t.setAmount(rs.getDouble("amount"));
                t.setType(rs.getString("type"));
                t.setDate(rs.getTimestamp("date"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }

    public static List<Transaction> getTransactionsByAccount(String accountNumber) {
        List<Transaction> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT t.id, t.account_id, t.amount, t.type, t.date " +
                    "FROM transactions t JOIN accounts a ON t.account_id = a.id " +
                    "WHERE a.account_number = ? ORDER BY t.date DESC";

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, accountNumber);

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getInt("id"));
                t.setAccountId(rs.getInt("account_id"));
                t.setAmount(rs.getDouble("amount"));
                t.setType(rs.getString("type"));
                t.setDate(rs.getTimestamp("date"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
