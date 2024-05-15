package org.acme.javaspringvacaturebankbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;

@Service
public class mailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromMail;

    public String sendMail(String to, String cc, String subject, String message, MultipartFile[] files) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromMail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            for (int i = 0; i < files.length; i++) {
                mimeMessageHelper.addAttachment(
                        files[i].getOriginalFilename(),
                        new ByteArrayResource(files[i].getBytes()));
            }

            mailSender.send(mimeMessage);
            return "mail sent";

        } catch (Exception e) {
            return "error sending mail: " + e;
        }

    }
}
