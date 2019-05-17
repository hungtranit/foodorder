package edu.hcmuaf.food_order.util;

import edu.hcmuaf.food_order.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

public class UserUtil {

    @Autowired
    UserRepository userRepository;

    public static String encryptPassword(String password) {
        final byte[] defaultBytes = password.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();
            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            password = hexString + "";
        } catch (final NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return password;
    }

//    public boolean sendMail(String mail) {
//        String newPassword = randomPassword(14);
//        userRepository.save(mail, encryptPassword(newPassword));
//        updatePassword(mail, encryptPassword(newPassword));
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("congminh0859@gmail.com", "64446644");
//            }
//        });
//        try {
//            Message message = new MimeMessage(session);
//            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
//            message.setFrom(new InternetAddress("congminh0859@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
//            message.setSubject("KOI KICHI create new password");
//            message.setText("new password is: " + newPassword);
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }

    private String randomPassword(int len) {
        String AB = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

}
