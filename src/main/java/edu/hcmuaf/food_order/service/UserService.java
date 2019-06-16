package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean login(String username, String password) {
        if (userRepository.getOne(username).getUsername().equalsIgnoreCase(username) &&
                userRepository.getOne(username).getPassworduser().equals(UserUtil.encryptPassword(password))) {
            return true;
        }
        return false;
    }

    public boolean sendMail(String mail, String username) {
        String newPassword = UserUtil.randomPassword(14);
        System.out.println("new password: " + newPassword);
        userRepository.updatePassword(UserUtil.encryptPassword(newPassword), username);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("congminh0859@gmail.com", "congminh0966130859");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("congminh0859@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Create new password");
            message.setText("New password is: " + newPassword);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
