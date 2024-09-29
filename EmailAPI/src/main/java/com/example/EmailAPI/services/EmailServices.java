package com.example.EmailAPI.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//@Component
@Service
public class EmailServices {

    public boolean sendEmail(String subject, String message, String to){
        String host = "smtp.gmail.com";
        String from = "YOUR_EMAIL";
        boolean f = false;
        Properties properties = System.getProperties();
        System.out.println("Properties: "+properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
//                return super.getPasswordAuthentication();
                return new PasswordAuthentication(from, "YOUR_PASSWORD");
            }
        });

        session.setDebug(true);
        MimeMessage m = new MimeMessage(session);
        try{
            m.setFrom(from);
            m.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});
            m.setSubject(subject);
            m.setText(message);
            Transport.send(m);
            System.out.println("Sent message successfully....");
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;

    }

}
