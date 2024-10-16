package org.example.projectoop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconn {
    private static Connection con;

    public void Connect() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "galaxia");
            System.out.println("connection successful");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
