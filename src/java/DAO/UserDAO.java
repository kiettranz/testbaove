/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entites.Users;
import Model.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kiett
 */
public class UserDAO {

    private Connection con;
    private ResultSet rs;
    private Statement stmt;

    public UserDAO() {
    }

    public Users[] checkUser(String username, String password) {
        try {
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            String sql = "select * from Users where username like '" + username + "' and password like '" + password + "' and del = 0";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            List<Users> list = new ArrayList();
            while (rs.next()) {
                String username1 = rs.getString(1);
                String password1 = rs.getString(2);
                String fullname1 = rs.getString(3);
                String id_staff1 = rs.getString(4);
                String role1 = rs.getString(5);
                Users u1 = new Users(username1, password1, fullname1, id_staff1, role1);
                list.add(u1);
            }
            if (list.size() <= 0) {
                return null;
            }
            Users[] result = new Users[list.size()];
            list.toArray(result);
            rs.close();
            stmt.close();
            con.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUsername(String username) {
        try {
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            String sql = "Select * from Users where username like '" + username + "' and del = 0";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Users[] searchUser(String username) {

        try {
            List<Users> list = new ArrayList();
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            String sql = "select * from Users where username like '%" + username + "%' and del = 0";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String username1 = rs.getString(1);
                String password1 = rs.getString(2);
                String fullname1 = rs.getString(3);
                String id_staff1 = rs.getString(4);
                String role1 = rs.getString(5);
                Users u1 = new Users(username1, password1, fullname1, id_staff1, role1);
                list.add(u1);
            }
            if (list.size() <= 0) {
                return null;
            }
            Users[] result = new Users[list.size()];
            list.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean addUser(Users users) throws SQLException {
        try {
            List<Users> list = new ArrayList();
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            String sql = "insert into users values('" + users.getUsername() + "',"
                    + "'" + users.getPassword()
                    + "',N'" + users.getFullname() + "','" + users.getId_staff()
                    + "','" + users.getRole() + "',0)";
            stmt = con.createStatement();
            int i = stmt.executeUpdate(sql);
            System.out.println("Đã thêm" + i);
            stmt.close();
            con.close();
            return true;
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            return false;
        }

    }

    public void updateUser(String username, String password, String fullname, String role) {
        try {
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            stmt = con.createStatement();
            String sql = "Update Users "
                    + "Set password='" + password + "',"
                    + "fullname=N'" + fullname + "',"
                    + "role='" + role + "' "
                    + "Where username like '" + username + "'";
            int i = stmt.executeUpdate(sql);
            System.out.println("Đã cập nhật" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delUser(String username) {
        try {
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            stmt = con.createStatement();
            String sql = "Update Users "
                    + "Set del=1 "
                    + "Where username like '" + username + "'";
            int i = stmt.executeUpdate(sql);
            System.out.println("Đã xóa" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
