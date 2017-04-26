/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entites.Departs;
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
public class DepartDAO {

    public DepartDAO() {
    }
    private Connection con;
    private Statement st;
    private ResultSet rs;
    public Departs[] getDepart(){
        try {
            List<Departs> list = new ArrayList();
            Connect c = new Connect();
            con=c.getSQLServerConnection();
            st=con.createStatement();
            String sql ="select * from Departs";
            rs=st.executeQuery(sql);
            while(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                Departs d = new Departs(id, name);
                list.add(d);
            }
            Departs[] result = new Departs[list.size()];
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
}
