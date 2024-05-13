package com.webshop.service;

import com.webshop.model.Korisnik;
import com.webshop.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;

    public Korisnik login(String korisnickoIme, String lozinka) {
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme);
        if (korisnik == null || !lozinka.equals(korisnik.getLozinka())) {
            return null;
        }
        return korisnik;
    }

    public boolean daLiKorisnikVecPostoji(String korisnickoIme, String mejlAdresa) {
        Korisnik korisnik = korisnikRepository.findByKorisnickoImeOrMejlAdresa(korisnickoIme, mejlAdresa);
        if(korisnik == null) return false;
        return true;
    }

    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

}
