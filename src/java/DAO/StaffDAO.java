/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entites.PersonTop10;
import Entites.Staffs;
import Entites.Users;
import Model.Connect;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kiett
 */
public class StaffDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public StaffDAO() {
    }

    public PersonTop10[] getPersons() {
        try {
            Date today = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            String[] year = sdf.format(today).split("-");
            List<PersonTop10> list = new ArrayList();
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "select TOP 10 s.id_staff,\n"
                    + "(select Name from Staffs where id_staff=s.id_staff) as Name,\n"
                    + "(select d1.name from Staffs s1 join Departs d1 on s1.Depart_id=d1.Depart_id where id_staff=s.id_staff) as Depart,\n"
                    + "(select Photo from Staffs where id_staff=s.id_staff) as Photo,\n"
                    + "SUM(case when r.type =1 then 1 else 0 end) as Bonus, \n"
                    + "SUM(case when r.type =0 then -1 else 0 end) as Violate\n"
                    + "from Records r join Staffs s on r.id_staff = s.id_staff\n"
                    + "where YEAR(r.date) = '" + year[0] + "'\n"
                    + "group by s.id_staff\n"
                    + "order by SUM(case when r.type =1 then 1 else 0 end)+SUM(case when r.type =0 then -1 else 0 end) desc ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String depart = rs.getString(3);
                String photo = rs.getString(4);
                int bonus = rs.getInt(5);
                int violate = rs.getInt(6);
                PersonTop10 a = new PersonTop10(id, name, depart, photo, bonus, violate);
                list.add(a);
            }
            PersonTop10[] result = new PersonTop10[list.size()];
            list.toArray(result);
            rs.close();
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Staffs[] searchStaff(String id) {
        try {
            List<Staffs> list = new ArrayList();
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "select s.id_staff,s.Name,s.Gender,s.Birthday,s.Photo,s.Email,s.Phone,s.Salary,d.name,s.Notes \n"
                    + "from Staffs s join Departs d on s.Depart_id=d.Depart_id\n"
                    + "where id_staff like '%" + id + "%' and del=0";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String id_staff = rs.getString(1);
                String name = rs.getString(2);
                Boolean gender = rs.getBoolean(3);
                String birthday = rs.getString(4);
                String photo = rs.getString(5);
                String email = rs.getString(6);
                String phone = rs.getString(7);
                Double salary = rs.getDouble(8);
                String depart = rs.getString(9);
                String note = rs.getString(10);
                Staffs s = new Staffs(id_staff, name, gender, birthday, photo, email, phone, salary, note, depart);
                list.add(s);
            }
            Staffs[] result = new Staffs[list.size()];
            list.toArray(result);
            rs.close();
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public String getNewId() {
        try {
            String oldid = "";
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "select MAX(id) from Staffs";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                id++;
                oldid += id;
            }
            String newid = "";
            if (oldid.length() == 1) {
                newid = "PS0000" + oldid;
            }
            if (oldid.length() == 2) {
                newid = "PS000" + oldid;
            }
            if (oldid.length() == 3) {
                newid = "PS00" + oldid;
            }
            if (oldid.length() == 4) {
                newid = "PS0" + oldid;
            }
            if (oldid.length() > 4) {
                newid = "PS" + oldid;
            }
            rs.close();
            st.close();
            con.close();
            return newid;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addStaff(Staffs staff) throws SQLException {
        try {

            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            byte gender = 0;
            if (staff.isGender() == true) {
                gender = 1;
            }

            String sql = "insert into Staffs values('" + staff.getId_staff() + "',N'" + staff.getName() 
                    + "'," + gender + ",'" + staff.getBirthday() + "',\n"
                    + " '" + staff.getPhoto() + "','" + staff.getEmail() + "','" + staff.getPhone() + "',\n"
                    + "" + staff.getSalary() + ",'" + staff.getNote() + "'," + staff.getDepart() + ",0) \n ";

            int i = st.executeUpdate(sql);

            st.close();
            con.close();
            if (i >= 1) {
                Users user = new Users(staff.getEmail(), "!@#$%^&*", staff.getName(), staff.getId_staff(), "Staff");
                UserDAO ud = new UserDAO();
                boolean flag = ud.addUser(user);
                if (flag == false) {
                    throw new BatchUpdateException();
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            return false;
        }
    }

    public boolean delStaff(String id_staff) {
        try {
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "Update Staffs Set del=1 where id_staff like '" + id_staff.trim() + "'";
            int i = st.executeUpdate(sql);
            st.close();
            con.close();
            if (i >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStaff(Staffs staff) {
        try {
            byte gender = 0;
            if (staff.isGender() == true) {
                gender = 1;
            }
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "Update Staffs Set Name=N'" + staff.getName() + "',Gender=" + gender 
                    + ",Birthday='" + staff.getBirthday() + "',\n"
                    + "Photo='" + staff.getPhoto().trim() + "',Email='" + staff.getEmail() 
                    + "',Phone='" + staff.getPhone() + "',\n"
                    + "Salary=" + staff.getSalary() + ",Notes=N'" + staff.getNote() 
                    + "',Depart_id=" + staff.getDepart() + ",del=0 "
                    + "Where id_staff like '" + staff.getId_staff().trim() + "'";
            System.out.println(sql);
            int i = st.executeUpdate(sql);
            System.out.println(i);
            st.close();
            con.close();
            if (i >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
