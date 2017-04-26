/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

/**
 *
 * @author kiett
 */
public class Mail {
   @Autowired
    @Qualifier("mailSender")
    JavaMailSender mailer;
    public boolean sendMail(String to,String subject,String msg){
        try {
            //gui mail
            MimeMessage mail = mailer.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail,true);
            helper.setFrom("smlservermail@gmail.com", "smlservermail@gmail.com");
            helper.setTo(to);
            helper.setCc("smlservermail@gmail.com");
            helper.setReplyTo("smlservermail@gmail.com", "smlservermail@gmail.com");
            helper.setSubject(subject);
            helper.setText(msg, true);
            mailer.send(mail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
