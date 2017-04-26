/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RolesDAO;
import DAO.UserDAO;
import Entites.ListRole;
import Entites.Staffs;
import Entites.Users;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author kiett
 */
@Controller
@RequestMapping(value="/Login/")

public class LoginController {
    
    @RequestMapping(value="showForm")
    public String index(){
        return "FormLogin/form";
    }
    
    @ModelAttribute(value="Users")
    public List loadUsers1(){
        UserDAO ud = new UserDAO();
        Users[] result1 = ud.searchUser("");
        System.out.println(result1.length);
        List result = new ArrayList(result1.length-1);
        return result;
    }
    @ModelAttribute(value="Staff")
    public Staffs newStaff(){
        Staffs s = new Staffs();
        return s;
    }
    
    public List loadSelect(){
        RolesDAO rd = new RolesDAO();
        ListRole[] result = rd.loadData();
        List list = new ArrayList();
        for(int i=0;i<result.length;i++){
            list.add(result[i]);
        }
        return list;
    }
    
    @RequestMapping(params="btnSubmit")
    public String index2(@RequestParam("txtname") String name,
                         @RequestParam("txtpassword") String password,
                         ModelMap mm,HttpSession session){
        UserDAO ud = new UserDAO();
        Users[] result = ud.checkUser(name, password);
        if(result!=null){
            String role = result[0].getRole();
            switch(role){
                case "Admin":
                    Users[] result1 = ud.searchUser("");
                    mm.addAttribute("Users1",result1[0]);
                    mm.addAttribute("listRole",loadSelect());
                    session.setAttribute("Admin", "Admin");
                    return "EN/FormManageAccount/form";
                case "Admin(Staffs)":
                    
                    return "EN/FormManageStaffs/form";
                case "Admin(Record)":
                    return "EN/FormManageRecord/form";
            }
//            if(result[0].getRole().equalsIgnoreCase("admin")){
//                    Users[] result1 = ud.searchUser("");
//                    mm.addAttribute("Users1",result1[0]);
//                    return "EN/FormManageAccount/form";
//            }
        }
        mm.addAttribute("alert","Username or Password was wrong");
            return "FormLogin/form";
    }
    
    
}
