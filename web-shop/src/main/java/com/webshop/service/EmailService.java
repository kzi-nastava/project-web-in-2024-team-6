/*package com.webshop.service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendAuctionEndNotification(Korisnik buyer, Korisnik seller, Proizvod proizvod) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(buyer.getEmail());
            helper.setSubject("Auction Ended: " + proizvod.getNaziv());
            helper.setText("Dear " + buyer.getIme() + ",\n\nThe auction for the product '" + proizvod.getNaziv() + "' has ended. You are the winner!\n\nThank you for participating.");

            javaMailSender.send(message);

            // Send email to seller
            // You can add similar logic to send an email to the seller
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle any exceptions or log errors
        }
    }
}*/


