package com.brrad.testdb;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class PreApp 
{
    public static void main( String[] args )
    {

        String jdbc_url = "jdbc:mysql://localhost:3306/learnjdbc";
        String jdbc_user = "root";
        String jdbc_password = "mysql#123";

        try (
            Connection conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_password);
            PreparedStatement ps = conn.prepareStatement("select id, grade, name, gender from students where gender=? and grade=?");

        ){
            ps.setInt(1, 1);
            ps.setLong(2, 1); 
            try (
                ResultSet rs = ps.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
