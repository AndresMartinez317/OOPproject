package org.example.projectoop;

import java.sql.*;

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
    public void Add(String fname,int mark1, int mark2) throws SQLException {
        float total = (float) (mark1 + mark2) /2;
        String query = "insert into Marks (Fname, Mark1,Mark2, Total) Values( "+"'"+fname+"'"+","+mark1+","+mark2 +","+total+")";
        try {
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet show() throws SQLException {
        String query = "Select * from Marks";
        Statement stm = con.createStatement();
        return stm.executeQuery(query);
    }
}
