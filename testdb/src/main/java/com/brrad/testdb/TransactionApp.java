package com.brrad.testdb;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Hello world!
 *
 */
public class TransactionApp
{
    public static void main( String[] args )
    {

        String jdbc_url = "jdbc:mysql://localhost:3306/learnjdbc";
        String jdbc_user = "root";
        String jdbc_password = "mysql#123";

        try (
            Connection conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_password);
        ) {

            try {
                conn.setAutoCommit(false);

                try (
                    PreparedStatement ps = conn.prepareStatement("select id, grade, name, gender from students");
                ) {
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

                try (
                    PreparedStatement ps = conn.prepareStatement("select name, grade from students");
                ) {
                    try (
                        ResultSet rs = ps.executeQuery();
                    ) {
                        while (rs.next()) {
                            String name = rs.getString(1);                            
                            long grade = rs.getLong(2);
                            System.out.printf("%s %d\n", name, grade);
                        }
                    } catch (Exception e) {                 
                        e.printStackTrace();
                    }                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}