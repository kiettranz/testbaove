/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entites.RecordDepart;
import Entites.RecordStaff;
import Model.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kiett
 */
public class RecordDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public RecordDAO() {
    }

    public RecordDepart[] getRecordDepartYear(String year) {
        try {
            List list = new ArrayList();
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "SELECT Depart,SUM(Bonus) as Bonus,SUM(Violate) as Violate \n"
                    + "FROM \n"
                    + "(select s.id_staff,\n"
                    + "(select Name from Staffs where id_staff=s.id_staff) as Name,\n"
                    + "(select d1.name from Staffs s1 join Departs d1 on s1.Depart_id=d1.Depart_id where id_staff=s.id_staff) as Depart,\n"
                    + "(select Email from Staffs where id_staff=s.id_staff) as Email,\n"
                    + "SUM(case when r.type =1 then 1 else 0 end) as Bonus, \n"
                    + "SUM(case when r.type =0 then -1 else 0 end) as Violate\n"
                    + "from Records r join Staffs s on r.id_staff = s.id_staff\n"
                    + "where YEAR(r.date) = '"+year+"'\n"
                    + "group by s.id_staff) as abc\n"
                    + "GROUP BY Depart";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString(1);
                int bonus = rs.getInt(2);
                int violate = rs.getInt(3);
                RecordDepart rd = new RecordDepart(name, bonus, violate);
                list.add(rd);
            }
            RecordDepart[] result = new RecordDepart[list.size()];
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

    public RecordStaff[] getRecordStaffYear(String year) {
        try {
            List list = new ArrayList();
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "select s.id_staff,\n"
                    + "(select Name from Staffs where id_staff=s.id_staff) as Name,\n"
                    + "(select d1.name from Staffs s1 join Departs d1 on s1.Depart_id=d1.Depart_id where id_staff=s.id_staff) as Depart,\n"
                    + "(select Email from Staffs where id_staff=s.id_staff) as Email,\n"
                    + "SUM(case when r.type =1 then 1 else 0 end) as Bonus, \n"
                    + "SUM(case when r.type =0 then -1 else 0 end) as Violate\n"
                    + "from Records r join Staffs s on r.id_staff = s.id_staff \n"
                    + "where YEAR(r.date) ='" + year + "'\n"
                    + "group by s.id_staff";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String depart = rs.getString(3);
                String email = rs.getString(4);
                int bonus = rs.getInt(5);
                int violate = rs.getInt(6);
                RecordStaff a = new RecordStaff(id, name, depart, email, bonus, violate);
                list.add(a);
            }
            RecordStaff[] result = new RecordStaff[list.size()];
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

    public RecordDepart[] getRecordDepart(String beginDate, String endDate) {
         try {
            List list = new ArrayList();
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "SELECT Depart,SUM(Bonus) as Bonus,SUM(Violate) as Violate \n"
                    + "FROM \n"
                    + "(select s.id_staff,\n"
                    + "(select Name from Staffs where id_staff=s.id_staff) as Name,\n"
                    + "(select d1.name from Staffs s1 join Departs d1 on s1.Depart_id=d1.Depart_id where id_staff=s.id_staff) as Depart,\n"
                    + "(select Email from Staffs where id_staff=s.id_staff) as Email,\n"
                    + "SUM(case when r.type =1 then 1 else 0 end) as Bonus, \n"
                    + "SUM(case when r.type =0 then -1 else 0 end) as Violate\n"
                    + "from Records r join Staffs s on r.id_staff = s.id_staff\n"
                    + "WHERE r.date BETWEEN '" + beginDate + "' AND '" + endDate + "'\n"
                    + "group by s.id_staff) as abc\n"
                    + "GROUP BY Depart";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString(1);
                int bonus = rs.getInt(2);
                int violate = rs.getInt(3);
                RecordDepart rd = new RecordDepart(name, bonus, violate);
                list.add(rd);
            }
            RecordDepart[] result = new RecordDepart[list.size()];
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

    public RecordStaff[] getRecordStaff(String beginDate, String endDate) {
        try {
            List list = new ArrayList();
            Connect c = new Connect();
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "SELECT s.id_staff,\n"
                    + "(select Name from Staffs where id_staff=s.id_staff) as Name,\n"
                    + "(select d1.name from Staffs s1 join Departs d1 on s1.Depart_id=d1.Depart_id where id_staff=s.id_staff) as Depart,\n"
                    + "(select Email from Staffs where id_staff=s.id_staff) as Email,\n"
                    + "SUM(case when r.type =1 then 1 else 0 end) as Bonus, \n"
                    + "SUM(case when r.type =0 then -1 else 0 end) as Violate\n"
                    + "FROM Records r join Staffs s on r.id_staff = s.id_staff \n"
                    + "WHERE r.date BETWEEN '" + beginDate + "' AND '" + endDate + "'\n"
                    + "GROUP BY s.id_staff";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String depart = rs.getString(3);
                String email = rs.getString(4);
                int bonus = rs.getInt(5);
                int violate = rs.getInt(6);
                RecordStaff a = new RecordStaff(id, name, depart, email, bonus, violate);
                list.add(a);
            }
            RecordStaff[] result = new RecordStaff[list.size()];
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

    public boolean insertRecord(String id, byte type, String reason, String date) {
        Connect c = new Connect();
        try {
            con = c.getSQLServerConnection();
            st = con.createStatement();
            String sql = "insert into Records values('" + id + "'," + type + ",N'" + reason + "','" + date + "',0)";
            int result = st.executeUpdate(sql);
            st.close();
            con.close();
            if (result >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
