package org.acme.javaspringvacaturebankbackend.controller;

import org.acme.javaspringvacaturebankbackend.service.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/mail")
public class mailController {

    @Autowired
    private mailService mailService;

    @PostMapping(value = "/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> sendMail(
            @RequestPart("to") String to,
            @RequestPart("cc") String cc,
            @RequestPart("subject") String subject,
            @RequestPart("message") String message,
            @RequestPart("files") MultipartFile[] files) {

        try {
            // Process the mail and files
            mailService.sendMail(to, cc, subject, message, files);
            return ResponseEntity.ok("Mail sent successfully.");
        } catch (Exception e) {
            // Handle any errors
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send mail: " + e.getMessage());
        }
    }
}