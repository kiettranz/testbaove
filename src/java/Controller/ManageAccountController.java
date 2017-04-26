/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RolesDAO;
import DAO.UserDAO;
import Entites.ListRole;
import Entites.Users;
import Model.ManageAccountModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping(value = "/Account-mgr/")
public class ManageAccountController {

    @RequestMapping(value = "showForm")
    public String index(ModelMap mm) {
        return "EN/FormManageAccount/form";
    }

    @ModelAttribute(value = "Users1")
    public Users loadUsers1() {
        UserDAO ud = new UserDAO();
        Users[] result1 = ud.searchUser("");
        return result1[0];
    }

    @ModelAttribute(value = "listRole")
    public List loadSelect() {
        RolesDAO rd = new RolesDAO();
        ListRole[] result = rd.loadData();
        List list = new ArrayList();
        for (int i = 0; i < result.length; i++) {
            list.add(result[i]);
        }
        return list;
    }

    @RequestMapping(params = "btnInsert")
    public String clickBtnInsert(@ModelAttribute(value = "Users1") Users users,
            ModelMap mm
    ) throws SQLException {
        boolean flag = true;
        ManageAccountModel mam = new ManageAccountModel();
        UserDAO ud = new UserDAO();
        byte flag1 = mam.validateUsername(users.getUsername());
        if (users.getPassword().length() < 7) {
            mm.addAttribute("alertPassword", "The password must be longer than 7 characters");
            flag = false;
        }
        if (flag1 == -1) {
            mm.addAttribute("alertUsername", "Your username is avaiable");
            flag = false;
        }
        if (flag1 == 1) {
            mm.addAttribute("alertUsername", "Username is incorrect");
            flag = false;
        }
        if (flag1 == 2) {
            mm.addAttribute("alertUsername", "The username must be longer than 7 characters");
            flag = false;
        }
        if (flag1 == 3) {
            mm.addAttribute("alertUsername", "The username have incorrect character and length must be longer than 7 characters");
            flag = false;
        }
        if (mam.validateFullname(users.getFullname()) == false) {
            mm.addAttribute("alertFullname", "Fullname is incorrect");
            flag = false;
        }
        if (flag == true) {
            ud.addUser(users);
            return "EN/FormManageAccount/form";
        }
        return "EN/FormManageAccount/form";
    }

    @RequestMapping(params = "btnUpdate")
    public String clickBtnUpdate1(@RequestParam(value = "PopupUsername") String username,
            @RequestParam(value = "PopupPassword") String password,
            @RequestParam(value = "PopupFullname") String fullname,
            @RequestParam(value = "PopupRole1") String role,
            ModelMap mm) {
        UserDAO ud = new UserDAO();
        ManageAccountModel mam = new ManageAccountModel();
        ud.updateUser(username, password, fullname, role);
        System.out.println("thành công");
        return "EN/FormManageAccount/form";
    }

    @RequestMapping(params = "btnReset")
    public String clickBtnReset() {
        return "EN/FormManageAccount/form";
    }

    @RequestMapping(params = "btnOK")
    public String clickBtnOk(@ModelAttribute(value = "Users1") Users users, @RequestParam(value = "PopupUsernameDel") String user) {
        UserDAO ud = new UserDAO();
        ud.delUser(user);
        return "EN/FormManageAccount/form";
    }

}
