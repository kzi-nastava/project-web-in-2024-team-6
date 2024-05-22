package com.webshop.service;

import com.webshop.model.PrijavaProfila;
import com.webshop.repository.PrijavaProfilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrijavaProfilaService {

    @Autowired
    private PrijavaProfilaRepository prijavaProfilaRepository;

    public void save(PrijavaProfila prijavaProfila) {
        prijavaProfilaRepository.save(prijavaProfila);
    }

}
