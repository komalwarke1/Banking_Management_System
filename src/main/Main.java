package main;

import util.DBConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Database Connected Successfully!");
        } else {
            System.out.println("Connection Failed");
        }
    }
}
