package com.webshop.service;

import com.webshop.dto.KorisnikDto;
import com.webshop.dto.PrijavaProfilaDto;
import com.webshop.dto.ProizvodDto;
import com.webshop.dto.RecenzijaDto;
import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.model.Proizvod;
import com.webshop.model.Recenzija;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.ProizvodRepository;
import com.webshop.repository.RecenzijaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.history.Revision;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    private ProizvodService proizvodService;
    @Autowired
    private ProizvodRepository proizvodRepository;
    @Autowired
    private RecenzijaService recenzijaService;
    @Autowired
    private PrijavaProfilaService prijavaProfilaService;

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

    public boolean oceniProdavca(RecenzijaDto recenzijaDto, Korisnik korisnik){

        Optional<Proizvod> proizvodOptional = proizvodService.getProizvod(recenzijaDto.getProizvodDto().getId());

        Proizvod proizvod = new Proizvod();

        if(proizvodOptional.isPresent()) {
            proizvod = proizvodOptional.get();
        }

        if(proizvod.isOstavljenaRecenzijaOdStraneKupca()) return false;

        Recenzija recenzija = new Recenzija(recenzijaDto.getOcena(), recenzijaDto.getKomentar(), LocalDate.now(), korisnik);
        recenzijaService.save(recenzija);


        proizvod.getProdavac().getRecenzije().add(recenzija);
        proizvodRepository.save(proizvod);
        proizvod.setOstavljenaRecenzijaOdStraneKupca(true);
        proizvodRepository.save(proizvod);

        return true;

    }

    public boolean oceniKupca(RecenzijaDto recenzijaDto, Korisnik korisnik){

        Optional<Proizvod> proizvodOptional = proizvodService.getProizvod(recenzijaDto.getProizvodDto().getId());

        Proizvod proizvod = new Proizvod();

        if(proizvodOptional.isPresent()) {
            proizvod = proizvodOptional.get();
        }

        if(proizvod.isOstavljenaRecenzijaOdStraneProdavca()) return false;

        Recenzija recenzija = new Recenzija(recenzijaDto.getOcena(), recenzijaDto.getKomentar(), LocalDate.now(), korisnik);
        recenzijaService.save(recenzija);

        Korisnik kupac = proizvodService.findKorisnikKojiJeKupioProizovd(proizvod);

        kupac.getRecenzije().add(recenzija);
        korisnikRepository.save(kupac);
        proizvod.setOstavljenaRecenzijaOdStraneProdavca(true);
        proizvodRepository.save(proizvod);

        return true;

    }

    public List<Recenzija> pregledajDateRecenzije(Korisnik korisnik){

        List<Korisnik> korisnci = korisnikRepository.findAll();
        List <Recenzija> recenzijaList = new ArrayList<>();

        for(Korisnik k : korisnci) {
            for(Recenzija r : k.getRecenzije()) {
                if(r.getKorisnik() == korisnik) recenzijaList.add(r);
            }
        }

        return recenzijaList;

    }

    public List<Recenzija> pregledajPrimljneRecenzije(Korisnik korisnik){

        List<Recenzija> recenzije = new ArrayList<>();

        for(Recenzija r : korisnik.getRecenzije()) {
            Korisnik kojiJeDaoRecenziju = r.getKorisnik();
            for (Recenzija rec : kojiJeDaoRecenziju.getRecenzije()) {
                if(rec.getKorisnik() == korisnik) recenzije.add(r);
            }
        }
        return recenzije;
    }

    public boolean prijaviKorisnika(Korisnik korisnik, PrijavaProfilaDto prijavaProfilaDto){

        PrijavaProfila prijava = new PrijavaProfila();

        prijava.setDatumPodnosenjaPrijave(LocalDate.now());
        prijava.setRazlogPrijave(prijavaProfilaDto.getPrijava().getRazlogPrijave());
        prijava.setStatusPrijave(PrijavaProfila.statPrijave.podneta);
        prijava.setKorisnikKojiJePodneoPrijavu(korisnik);
        prijava.setKorisnikNaKogaSeOdnosiPrijavu(prijavaProfilaDto.getProizvod().getProdavac());

        prijavaProfilaService.save(prijava);

        return true;
    }

}
