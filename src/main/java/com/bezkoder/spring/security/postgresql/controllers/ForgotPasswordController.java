package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.service.UserServiceImpl;
import com.bezkoder.spring.security.postgresql.util.Utility;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/forgot_password")
@RequiredArgsConstructor
public class ForgotPasswordController {
    private final UserServiceImpl userService;
    private final JavaMailSender mailSender;
    @PostMapping("/getEmail")
    public  String processForgotPassword(HttpServletRequest request){
        String email=request.getParameter("email");
        String token= RandomString.make(45);

        try {
            userService.updateResetPasswordToken(token,email.toLowerCase());

            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            System.out.println(resetPasswordLink);
            sendEmail(email, resetPasswordLink);
            System.out.println("Email: "+email);
            System.out.println("Token   : "+token);
            return "We have sent a reset password link to your email. Please check.";
        } catch (Exception e) {
            return "We have error while sending email";
        }
    }
    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("Nataschandreeva83@gmail.com", "Kafka");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
        try{
            helper.setSubject(subject);

            helper.setText(content, true);

            mailSender.send(message);}catch(Exception exception){
            System.out.println(exception);
        }
    }
}
