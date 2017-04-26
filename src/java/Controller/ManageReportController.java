/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RecordDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author kiett
 */
@Controller
@RequestMapping(value = "/Report/")
public class ManageReportController {

    @RequestMapping(value = "index")
    public String index(ModelMap mm) {
        RecordDAO rd = new RecordDAO();
        SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
        // Format the date to Strings
        String date = mdyFormat.format(new Date());
        String[] date1 = date.split("-");
        mm.addAttribute("Department", rd.getRecordDepartYear(date1[2]));
        mm.addAttribute("Staff", rd.getRecordStaffYear(date1[2]));
        return "EN/FormManageDeparts/form";
    }

    @RequestMapping(value = "search")
    public String search(
            @RequestParam(value = "beginDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
            @RequestParam(value = "endDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            ModelMap mm) {
        System.out.println(beginDate);
        if (beginDate == null || endDate == null) {
            beginDate = new Date();
            endDate = new Date();
        }
        RecordDAO rd = new RecordDAO();
        SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
        String beginDate1 = mdyFormat.format(beginDate);
        String endDate1 = mdyFormat.format(endDate);
        System.out.println(beginDate1 + "==>" + endDate1);
        mm.addAttribute("Department", rd.getRecordDepart(beginDate1, endDate1));
        mm.addAttribute("Staff", rd.getRecordStaff(beginDate1, endDate1));
        return "EN/FormManageDeparts/form";
    }

}
