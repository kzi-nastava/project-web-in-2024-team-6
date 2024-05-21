package com.webshop.service;
import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


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
            helper.setTo(buyer.getMejlAdresa());
            helper.setSubject("Auction Ended: " + proizvod.getNaziv());
            helper.setText("Dear " + buyer.getIme() + ",\n\nThe auction for the product '" + proizvod.getNaziv() + "' has ended. You are the winner!\n\nThank you for participating.");

            javaMailSender.send(message);

            // Send email to seller
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


