package com.webshop.service;

import com.webshop.model.Korisnik;
import com.webshop.model.Recenzija;
import com.webshop.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;

    public Recenzija save(Recenzija recenzijaZaCuvanje) {
        return recenzijaRepository.save(recenzijaZaCuvanje);
    }

    public List<Recenzija> vratiSveRecenzije() {
        return recenzijaRepository.findAll();
    }

    public void izmeniRecenziju(Long reviewId, String newComment) {
        Recenzija review = recenzijaRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review with ID " + reviewId + " not found."));
        review.setKomentar(newComment);
        recenzijaRepository.save(review);
    }

    public void izbrisiRecenziju(Long reviewId) {
        recenzijaRepository.deleteById(reviewId);
    }
}
