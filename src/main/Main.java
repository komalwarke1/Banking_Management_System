package main;

import util.DBConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Database sonnected successfully!");
        } else {
            System.out.println("Connection failed to connect");
        }
    }
}
