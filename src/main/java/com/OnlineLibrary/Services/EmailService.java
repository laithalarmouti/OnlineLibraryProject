
package com.OnlineLibrary.Services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendBookRequest(String to, String bookTitle,
                                String requesterEmail, String message,
                                boolean withMeeting) {
        String subject = withMeeting ?
                "Book Discussion Request: " + bookTitle :
                "Book Return Request: " + bookTitle;

        String body = withMeeting ?
                createMeetingRequestBody(bookTitle, requesterEmail, message) :
                createSimpleRequestBody(bookTitle, requesterEmail, message);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("eutropiuz@gmail.com");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);
        mailSender.send(email);
    }


    private String createSimpleRequestBody(String bookTitle, String requesterEmail, String message) {
        return String.format(
                "Hello,\n\n" +
                        "%s would like to borrow '%s'.\n\n" +
                        "Message: %s\n\n" +
                        "Please return the book when convenient.\n" +
                        "Contact: %s\n\nRegards,\nLibrary Team",
                requesterEmail, bookTitle, message, requesterEmail
        );
    }
    private String createMeetingRequestBody(String bookTitle, String requesterEmail, String message) {
        return String.format(
                "Hello,\n\n" +
                        "%s would like to discuss the book '%s' with you!\n" +
                        "Message from requester:\n%s\n\n" +
                        "Suggested discussion points:\n" +
                        "1. Key themes and characters\n" +
                        "2. Favorite chapters/scenes\n" +
                        "3. Potential book club discussion\n\n" +
                        "Please coordinate a meeting time via email.\n" +
                        "Requester's contact: %s\n\n" +
                        "Best regards,\n" +
                        "The Online Library Team",
                requesterEmail, bookTitle, message, requesterEmail
        );
    }
}