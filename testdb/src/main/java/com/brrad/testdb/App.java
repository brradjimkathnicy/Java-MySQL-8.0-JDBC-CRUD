package com.brrad.testdb;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        String jdbc_url = "jdbc:mysql://localhost:3306/learnjdbc";
        String jdbc_user = "root";
        String jdbc_password = "mysql#123";

        try (
            Connection conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id, grade, name, gender from students");
        ) {
            while (rs.next()) {
                long id = rs.getLong(1);
                long grade = rs.getLong(2);
                String name = rs.getString(3);
                int gender = rs.getInt(4);
                System.out.printf("%d %d %s %d\n", id, grade, name, gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
