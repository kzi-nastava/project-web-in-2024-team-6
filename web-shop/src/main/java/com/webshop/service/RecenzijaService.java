package com.webshop.service;

import com.webshop.model.Recenzija;
import com.webshop.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;

    public Recenzija save(Recenzija recenzijaZaCuvanje) {
        return recenzijaRepository.save(recenzijaZaCuvanje);
    }

}
