/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RecordDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author kiett
 */
@Controller
@RequestMapping(value = "/Record/")
public class ManageRecordController {

    @RequestMapping(value = "index")
    public String index() {
        return "EN/FormManageRecord/form";
    }
    
    @Autowired
    @Qualifier("mailSender")
    JavaMailSender mailer;

    @RequestMapping(value = "goodReport")
    public String goodReport(@RequestParam(value = "bonusId") String id,
            @RequestParam(value = "goodReason") String reason,
            @RequestParam(value = "bonusEmail") String email,
            ModelMap mm) {
        try {
            SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
            // Format the date to Strings
            String date = mdyFormat.format(new Date());
            byte type = 1;
            RecordDAO rd = new RecordDAO();
            System.out.println("ID:" + id + "type=" + type + "reason:" + reason + "date:" + date);
            if (rd.insertRecord(id, type, reason, date) == true) {
                mm.addAttribute("msg", "Report success!");
                MimeMessage mail = mailer.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mail, true);
                helper.setFrom("smlservermail@gmail.com", "smlservermail@gmail.com");
                helper.setTo(email);
                helper.setCc("smlservermail@gmail.com");
                helper.setReplyTo("smlservermail@gmail.com", "smlservermail@gmail.com");
                helper.setSubject("Bonus");
                helper.setText("Reason: " + reason, true);
                mailer.send(mail);
                mm.addAttribute("msg1", "Send mail success!");
            } else {
                mm.addAttribute("msg1", "Send mail fail!");
                mm.addAttribute("msg", "Report fail! Please try again!");
            }

            return "EN/FormManageRecord/form";
        } catch (Exception e) {
            e.printStackTrace();
            return "EN/FormManageRecord/form";
        }
    }

    @RequestMapping(value = "badReport")
    public String badReport(@RequestParam(value = "violateId") String id,
            @RequestParam(value = "badReason") String reason,
            @RequestParam(value = "violateEmail") String email,
            ModelMap mm) {
        try {
            SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
            // Format the date to Strings
            String date = mdyFormat.format(new Date());
            byte type = 0;
            RecordDAO rd = new RecordDAO();
            if (rd.insertRecord(id, type, reason, date) == true) {
                mm.addAttribute("msg", "Report success!");
                mm.addAttribute("msg", "Report success!");
                MimeMessage mail = mailer.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mail, true);
                helper.setFrom("smlservermail@gmail.com", "smlservermail@gmail.com");
                helper.setTo(email);
                helper.setCc("smlservermail@gmail.com");
                helper.setReplyTo("smlservermail@gmail.com", "smlservermail@gmail.com");
                helper.setSubject("Violate");
                helper.setText("Reason: " + reason, true);
                mailer.send(mail);
                mm.addAttribute("msg1", "Send mail success!");
            } else {
                mm.addAttribute("msg1", "Send mail fail!");
                mm.addAttribute("msg", "Report fail! Please try again!");
            }

            return "EN/FormManageRecord/form";
        } catch (Exception e) {
            e.printStackTrace();
            return "EN/FormManageRecord/form";
        }
    }
}
