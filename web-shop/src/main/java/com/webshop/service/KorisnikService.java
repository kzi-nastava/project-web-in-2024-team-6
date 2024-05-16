package com.webshop.service;

import com.webshop.dto.KorisnikDto;
import com.webshop.model.Korisnik;
import com.webshop.repository.KorisnikRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Korisnik azurirajKorisnika(String id, KorisnikDto azuriranKorisnik){

        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Korisnik sa ID-jem " + id + " nije pronađen."));

        if (korisnik.getUloga() == Korisnik.TipKorisnika.Administrator) {
            throw new IllegalArgumentException("Korisnik sa ulogom Administrator ne može ažurirati profil.");
        }

        // Provera trenutne lozinke
        if (!korisnik.getLozinka().equals(azuriranKorisnik.getTrenutnaLozinka())) {
            throw new IllegalArgumentException("Trenutna lozinka nije ispravna.");
        }

        // Ažuriranje podataka
        korisnik.setIme(azuriranKorisnik.getIme());
        korisnik.setPrezime(azuriranKorisnik.getPrezime());
        korisnik.setDatumRodjenja(azuriranKorisnik.getDatumRodjenja());
        korisnik.setPutanjaDoSlike(azuriranKorisnik.getProfilnaSlika());
        korisnik.setOpis(azuriranKorisnik.getOpis());
        korisnik.setBrojTelefona(azuriranKorisnik.getBrojTelefona());

        // Ažuriranje korisničkog imena, mejl adrese i lozinke samo ako su promenjeni
        if (!korisnik.getKorisnickoIme().equals(azuriranKorisnik.getKorisnickoIme())) {
            korisnik.setKorisnickoIme(azuriranKorisnik.getKorisnickoIme());
        }
        if (!korisnik.getMejlAdresa().equals(azuriranKorisnik.getEmail())) {
            korisnik.setMejlAdresa(azuriranKorisnik.getEmail());
        }
        if (azuriranKorisnik.getNovaLozinka() != null && !azuriranKorisnik.getNovaLozinka().isEmpty()) {
            korisnik.setLozinka(azuriranKorisnik.getNovaLozinka());
        }

        return korisnikRepository.save(korisnik);
    }



    public Optional<Korisnik> nadjiPoId(String id){
        return korisnikRepository.findById(id);
    }

    public List<Korisnik> nadjiSve(){
       return korisnikRepository.findAll();
    }





}
