package com.brrad.testdb;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Hello world!
 *
 */
public class CrudApp 
{
    public static void main( String[] args )
    {

        String jdbc_url = "jdbc:mysql://localhost:3306/learnjdbc";
        String jdbc_user = "root";
        String jdbc_password = "mysql#123";

        try (
            Connection conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_password);
        ) {

            try (
                PreparedStatement ps = conn.prepareStatement("select id, grade, name, gender from students where gender=? and grade=?");
            ) {
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

            try (
                PreparedStatement ps = conn.prepareStatement("insert into students (grade, name, gender, score) values (?, ?, ?, ?)");
            ) {
                ps.setObject(1, 3);
                ps.setObject(2, "Kioo");
                ps.setObject(3, 1);
                ps.setObject(4, 99);
                int n = ps.executeUpdate();
                System.out.println(n);
            }catch (Exception e) {
                e.printStackTrace();
            }

            try (
                PreparedStatement ps = conn.prepareStatement("insert into students (grade, name, gender, score) values (?, ?, ?, ?)", 
                    Statement.RETURN_GENERATED_KEYS);
            ) {
                ps.setObject(1, 3);
                ps.setObject(2, "Nike");
                ps.setObject(3, 1);
                ps.setObject(4, 66);
                int n = ps.executeUpdate();
                System.out.println(n);
                try (
                    ResultSet rs = ps.getGeneratedKeys()
                ) {
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        System.out.println(id);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }            

            try (
                PreparedStatement ps = conn.prepareStatement("update students set name=? where id=?");
            ) {
                ps.setObject(1, "Liop");
                ps.setObject(2, 20);
                int n = ps.executeUpdate();
                System.out.println(n);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try (
                PreparedStatement ps = conn.prepareStatement("delete from students where grade=?");
            ) {
                ps.setObject(1, 1);
                int n = ps.executeUpdate();
                System.out.println(n);
            } catch (Exception e) {
                e.printStackTrace();
            }            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}