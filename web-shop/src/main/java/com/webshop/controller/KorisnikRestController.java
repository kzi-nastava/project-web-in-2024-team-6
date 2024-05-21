package com.webshop.controller;

import com.webshop.dto.KorisnikDto;
import com.webshop.dto.LoginDto;
import com.webshop.model.Korisnik;
import com.webshop.service.KorisnikService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession sesija) {

        if(loginDto.getKorisnickoIme() == null || loginDto.getLozinka() == null){
            return new ResponseEntity("Zahtev je poslat prazan.", HttpStatus.BAD_REQUEST);//bad req
        }

        if(loginDto.getKorisnickoIme().isBlank() || loginDto.getLozinka().isBlank()) {
            return new ResponseEntity("Niste uneli sve potrebne podatke za logovanje.", HttpStatus.BAD_REQUEST);//bad req
        }

        Korisnik ulogovaniKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if(ulogovaniKorisnik == null) {
            return new ResponseEntity("Korisnik sa tim korisnickim imenom i lozinkom ne postoji.", HttpStatus.NOT_FOUND);
        }

        sesija.setAttribute("korisnik", ulogovaniKorisnik);
        return ResponseEntity.ok("Usesno ste se ulogovali!");

    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession sesija) {
        Korisnik logovaniKorisnik = (Korisnik) sesija.getAttribute("korisnik");
        if(logovaniKorisnik == null)
            return new ResponseEntity("Akcija nije dozvoljena.", HttpStatus.FORBIDDEN);

        sesija.invalidate();
        return new ResponseEntity("Uspesno ste se odlogovali.", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity registerProdavac(@RequestBody Korisnik korisnik) {
        if(!daLiSuSviPodaciUneti(korisnik)) return new ResponseEntity("Neki od podataka za registraciju nisu uneti.",HttpStatus.BAD_REQUEST);
        if(korisnikService.daLiKorisnikVecPostoji(korisnik.getKorisnickoIme(), korisnik.getMejlAdresa())) return new ResponseEntity("Korisnik sa unetim podacima vec postoji.",HttpStatus.BAD_REQUEST);

        korisnik.setBlokiran(false);
        Korisnik k = korisnikService.save(korisnik);
        if(k == null) { return new ResponseEntity("Vasi podaci nisu sacuvani, molimo probajte ponovo.", HttpStatus.INTERNAL_SERVER_ERROR); }
        return new ResponseEntity("Vasa registracija je prihvacena, uspesno ste registrovali", HttpStatus.OK);
    }


    //provera da li postoje svi podaci(potrebni podaci: ime, prezime, jedinstveno korisničko ime, mejl adresa, broj telefona i lozinka)
    boolean daLiSuSviPodaciUneti(Korisnik korisnik){
        return  (korisnik.getUloga() != null) &&
                (korisnik.getIme() != null && !korisnik.getIme().isBlank()) &&
                (korisnik.getPrezime() != null && !korisnik.getPrezime().isBlank()) &&
                (korisnik.getKorisnickoIme() != null && !korisnik.getKorisnickoIme().isBlank()) &&
                (korisnik.getMejlAdresa() != null && !korisnik.getMejlAdresa().isBlank()) &&
                (korisnik.getBrojTelefona() != null && !korisnik.getBrojTelefona().isBlank()) &&
                (korisnik.getLozinka() != null && !korisnik.getLozinka().isBlank());
    }



    @PutMapping("/korisnici/{id}")
    public ResponseEntity<Korisnik> azurirajKorisnika(@PathVariable("id") String id,
                                                      @RequestBody KorisnikDto azuriranKorisnik/*,@RequestHeader("Uloga") String uloga*/,
                                                      HttpSession sesija) {

        Korisnik korisnik = (Korisnik) sesija.getAttribute("korisnik");
        if(korisnik.getUloga() == Korisnik.TipKorisnika.Prodavac || korisnik.getUloga() == Korisnik.TipKorisnika.Kupac){
            try {
                Korisnik azuriraniKorisnik = korisnikService.azurirajKorisnika(id, azuriranKorisnik);
                return new ResponseEntity<>(azuriraniKorisnik, HttpStatus.OK);
            } catch (EntityNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
    }


    @GetMapping("/korisnici")
    public ResponseEntity<List<Korisnik>> nadjiSveKorisnike(HttpSession sesija){
        Korisnik korisnik = (Korisnik) sesija.getAttribute("korisnik");
        if(korisnik.getUloga() == Korisnik.TipKorisnika.Prodavac || korisnik.getUloga() == Korisnik.TipKorisnika.Kupac){
            try{
                List<Korisnik> korisnici = korisnikService.nadjiSve();
                return new ResponseEntity<>(korisnici, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/korisnici/{id}")
    public ResponseEntity<Korisnik> prikaziKorisnika(@PathVariable("id") String id,HttpSession sesija){
        Korisnik korisnik = (Korisnik) sesija.getAttribute("korisnik");
        if(korisnik.getUloga() == Korisnik.TipKorisnika.Prodavac || korisnik.getUloga() == Korisnik.TipKorisnika.Kupac){
            try{
                Optional<Korisnik> k = korisnikService.nadjiPoId(id);
                return new ResponseEntity(k,HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity("Doslo je do greske",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
    }


}