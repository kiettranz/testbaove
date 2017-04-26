/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entites.ListRole;

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
public class RolesDAO {
    public ListRole[] loadData(){
            Connect c = new Connect();
            Connection conn = null;
            Statement st = null;
            ResultSet rs;
            List list = new ArrayList();
        try {
            conn = c.getSQLServerConnection();
            st = conn.createStatement();
            String sql = "select * from listRole";
            rs= st.executeQuery(sql);
            while(rs.next()){  
                int id = rs.getInt(1);
                String name = rs.getString(2);
                ListRole l = new ListRole(id, name);
                list.add(l);
            }
            ListRole[] result = new ListRole[list.size()];
            list.toArray(result);
            rs.close();
            st.close();
            conn.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();            
        }
        return null;
    }
}
