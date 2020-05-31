/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pidev.edu.gs.entities.Utilisateur;


/**
 *
 * @author safa
 */
public class Mail2 {
      Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
     public static void sendMail(String recepient) throws MessagingException {
        System.out.println("begin mail send");

        Properties properties = new Properties();

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        
   String monEmail = "youssef.nehdi@esprit.tn";
   
        
        String mdp = "193JMT0408";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(monEmail, mdp);
            }
            
        });
        
        Message message = prepareMessage(session, monEmail);
        Transport.send(message);
        System.out.println("message has been sent");
    }
    
    private static Message prepareMessage(Session session, String monMail) throws AddressException, MessagingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(monMail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(monMail));
        message.setSubject("Bienvenu");
        
        message.setText("Bienvenu a Debbou.Tn");
        return message;
    }
}

