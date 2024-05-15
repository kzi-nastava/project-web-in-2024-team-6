package com.webshop.service;

import com.webshop.dto.KorisnikDto;
import com.webshop.model.Korisnik;
import com.webshop.repository.KorisnikRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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


    public void azurirajProfil(Korisnik korisnik, KorisnikDto korisnikdto, String uloga) throws Exception {

       if (uloga.equals("Administrator")){
           throw new Exception("Nemate dozvolu da azurirate profiil. ");
       }

       if (!korisnik.getLozinka().equals(korisnikdto.getTrenutnaLozinka())) {
           throw new Exception("Pogresna trenutna lozinka. ");
       }

       korisnik.setDatumRodjenja(korisnikdto.getDatumRodjenja());
       korisnik.setPutanjaDoSlike(korisnikdto.getProfilnaSlika());
       korisnik.setOpis(korisnikdto.getOpis());
       korisnik.setIme(korisnikdto.getIme());
       korisnik.setPrezime(korisnikdto.getPrezime());
       korisnik.setBrojTelefona(korisnikdto.getBrojTelefona());

       if (korisnikdto.getNovaLozinka() != null && !korisnikdto.getNovaLozinka().isEmpty()){
           korisnik.setLozinka(korisnikdto.getNovaLozinka());
       }

       korisnik.setKorisnickoIme(korisnikdto.getKorisnickoIme());
       korisnik.setMejlAdresa(korisnikdto.getEmail());

    }

    public Korisnik nadjiPoId(Long Id){
        return korisnikRepository.findById(String.valueOf(id)).orElseThrow(() ->
                new EntityNotFoundException());
    }

}
