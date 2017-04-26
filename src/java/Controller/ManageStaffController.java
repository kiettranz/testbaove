/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.StaffDAO;
import Entites.Staffs;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author kiett
 */
@Controller
@RequestMapping(value = "/Staffs-mgr/")
public class ManageStaffController {

    @RequestMapping(value = "")
    public String index() {
        return "EN/FormManageStaffs/form";
    }

    @ModelAttribute(value = "Staff")
    public Staffs newStaff() {
        Staffs s = new Staffs();
        return s;
    }
    @Autowired
    ServletContext context;

    @RequestMapping(params = "btnInsert", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String btnInsert(
            @RequestParam(value = "txtName", defaultValue = "none") String name,
            @RequestParam(value = "txtDepart", defaultValue = "none") String depart,
            @RequestParam(value = "rdGender", defaultValue = "True") Boolean gender,
            @RequestParam(value = "txtDate", defaultValue = "01/01/2000") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
            @RequestParam(value = "txtEmail", defaultValue = "none") String email,
            @RequestParam(value = "txtPhone", defaultValue = "none") String phone,
            @RequestParam(value = "txtSalary", defaultValue = "0.0") Double salary,
            @RequestParam(value = "txtNote", defaultValue = "none") String note,
            @RequestParam(value = "txtPhoto") MultipartFile file) {
        try {
            StaffDAO sd = new StaffDAO();
            SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
            // Format the date to Strings
            String Staff_id = sd.getNewId();
            String newBirthday = mdyFormat.format(birthday);
            String newphoto;
            if(!file.isEmpty()){
            String photo = String.valueOf(file.getOriginalFilename());
            String[] type = photo.split("\\.", 0);
            newphoto = Staff_id + "." + type[1];
            }else{
                newphoto ="none.png";
            }
            

            // Results...
            Staffs staffs = new Staffs(Staff_id, name, gender, newBirthday, newphoto, email, phone, salary, note, depart);

            if (sd.addStaff(staffs) == true) {
                if (!file.isEmpty()) {
                    String photoPath = context.getRealPath("/resources/Photos/" + file.getOriginalFilename());
                    File oldFile = new File(photoPath);
                    
                    File newFile = new File(context.getRealPath("/resources/Photos/" + newphoto));

                    file.transferTo(oldFile);
                    if (oldFile.renameTo(newFile)) {
                        System.out.println("Rename succesful");
                        oldFile.delete();
                    } else {
                        System.out.println("Rename failed");
                    }
                    System.out.println("Added Susscess!");
                }
            } else {
                System.out.println("Added Fail!");
            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return "EN/FormManageStaffs/form";

    }

    @RequestMapping(value = "update", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String updateStaff(
            @RequestParam(value = "PopupId") String id,
            @RequestParam(value = "PopupName", defaultValue = "none") String name,
            @RequestParam(value = "PopupDepart", defaultValue = "none") String depart,
            @RequestParam(value = "PopupGender", defaultValue = "True") Boolean gender,
            @RequestParam(value = "PopupDate", defaultValue = "01/01/2000") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
            @RequestParam(value = "PopupEmail", defaultValue = "none") String email,
            @RequestParam(value = "PopupPhone", defaultValue = "none") String phone,
            @RequestParam(value = "PopupSalary", defaultValue = "0.0") Double salary,
            @RequestParam(value = "PopupNote", defaultValue = "none") String note,
            @RequestParam(value = "PopupNameImg") String oldphoto,
            @RequestParam(value = "PopupPhoto") MultipartFile file) {
        try {
            boolean flag=false;
            StaffDAO sd = new StaffDAO();
            SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
            // Format the date to Strings
            String newphoto = oldphoto;
            String newBirthday = mdyFormat.format(birthday);
            if (!file.isEmpty()) {
                flag=true;
                String photo = String.valueOf(file.getOriginalFilename());
                String[] type = photo.split("\\.", 0);
                newphoto = id.trim() + "." + type[1].trim();
            }
            // Results...
            Staffs staffs = new Staffs(id, name, gender, newBirthday, newphoto.trim(), email, phone, salary, note, depart);

            if (sd.updateStaff(staffs) == true) {
                if(flag==true){
                    String photoPath = context.getRealPath("/resources/Photos/" + file.getOriginalFilename());
                    File oldFile = new File(photoPath);
                    
                    File newFile = new File(context.getRealPath("/resources/Photos/" + newphoto.trim()));

                    file.transferTo(newFile);
//                    if (oldFile.renameTo(newFile)) {
//                        System.out.println("Rename succesful");
//                        oldFile.delete();
//                    } else {
//                        System.out.println("Rename failed");
//                    } 
                }
                 System.out.println("Update Susscess!");
            } else{System.out.println("Update fail1");}
            return "EN/FormManageStaffs/form";
        } catch (Exception e) {
            System.out.println("Update fail2");
            e.printStackTrace();
            return "EN/FormManageStaffs/form";
        }
    }
    @RequestMapping(value="delete", method = RequestMethod.POST)
    public String deleteStaff(@RequestParam(value="PopupIdDel") String id){
        try {
            StaffDAO sd =  new StaffDAO();
            if(sd.delStaff(id.trim())==true){
                System.out.println("Del success!");
                return "EN/FormManageStaffs/form";
            }
            return "EN/FormManageStaffs/form";
        } catch (Exception e) {
            e.printStackTrace();
            return "EN/FormManageStaffs/form";
        }
    }
}
