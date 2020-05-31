/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.utils;

import java.net.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author ASUS
 */
public class Mail3 {
    
    


    private static Message prepareMessage(Session session,String MyEmail,String recepient,String corps) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("password");
            message.setText(corps);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Mail3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static void SendMail(String recepient,String corps) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.transport.protocol", "smtp");
        
        String MyEmail="youssef.nehdi@esprit.tn";
        String mdpEmail="193JMt0408";
        
        Session session= Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MyEmail, mdpEmail);
            }          
        });
        
        Message message= prepareMessage(session,MyEmail,recepient,corps);

        Transport.send(message);
     System.out.println("mot de passse oublié est envoyé au mail 🙂 ");
        
    
    }
    
}