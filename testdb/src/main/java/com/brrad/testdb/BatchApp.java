package com.brrad.testdb;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;


public class BatchApp 
{
    public static void main( String[] args )
    {

        String jdbc_url = "jdbc:mysql://localhost:3306/learnjdbc";
        String jdbc_user = "root";
        String jdbc_password = "mysql#123";

        try (
            Connection conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_password);
        ){
            try (
                PreparedStatement ps = conn.prepareStatement("insert into students (name, gender, grade, score) values (?, ?, ?, ?)");
            ) {
                String[][] students = {
                    {"Giio", "1", "2", "78"},
                    {"Hjo", "0", "1", "88"},
                    {"Owo", "1", "2", "57"}
                };

                for (String[] s: students) {
                    ps.setObject(1, s[0]);
                    ps.setObject(2, Integer.valueOf(s[1]));
                    ps.setObject(3, Long.valueOf(s[2]));
                    ps.setObject(4, Long.valueOf(s[3]));
                    ps.addBatch();
                }

                int[] nb = ps.executeBatch();
                for (int n : nb) {
                    System.out.println(n + " inserted");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }   

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
