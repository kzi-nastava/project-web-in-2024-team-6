package com.webshop.controller;

import com.webshop.dto.ProizvodDto;

import com.webshop.dto.SearchDto;
import com.webshop.model.Kategorija;


import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/products")
public class ProizvodRestController {

    @Autowired
    private ProizvodService proizvodService;
    @Autowired
    private KorisnikService korisnikService;

    //treba pogledati da li tako ili ne
    @GetMapping
    public ResponseEntity<Page<ProizvodDto>> getProizvodi(@RequestParam int strana, HttpSession sesija) {
        Korisnik korisnik = (Korisnik) sesija.getAttribute("korisnik");
        if(korisnik == null) {
            Page<ProizvodDto> strProizvoda = proizvodService.getProizvodi(strana, 10);
            if (strProizvoda.isEmpty()) {
                return new ResponseEntity("Nema proizvoda!", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(strProizvoda);
        } else if(korisnik.getUloga() != Korisnik.TipKorisnika.Administrator){
            Page<ProizvodDto> strProizvoda = proizvodService.getProizvodi(strana, 10);
            if (strProizvoda.isEmpty()) {
                return new ResponseEntity("Nema proizvoda!", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(strProizvoda);
        }
        return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProizvodDto> getProizvod(@PathVariable("id") Long id, HttpSession sesija) {
        Korisnik korisnik = (Korisnik) sesija.getAttribute("korisnik");
        if(korisnik == null){
            Optional<Proizvod> proizvod = proizvodService.getProizvod(id);
            if(proizvod.isPresent()){
                ProizvodDto prozivodDto = new ProizvodDto(id ,proizvod.get().getNaziv(), proizvod.get().getOpis(), proizvod.get().getKategorija(), proizvod.get().getCena(), proizvod.get().getSlika(), proizvod.get().getTipProdaje());
                return ResponseEntity.status(HttpStatus.OK).body(prozivodDto);
            }
            else return new ResponseEntity("Takav proizvod ne postoji!", HttpStatus.NOT_FOUND);
        } else if(korisnik.getUloga() != Korisnik.TipKorisnika.Administrator){
            Optional<Proizvod> proizvod = proizvodService.getProizvod(id);
            if(proizvod.isPresent()){
                ProizvodDto prozivodDto = new ProizvodDto(id ,proizvod.get().getNaziv(), proizvod.get().getOpis(), proizvod.get().getKategorija(), proizvod.get().getCena(), proizvod.get().getSlika(), proizvod.get().getTipProdaje());
                return ResponseEntity.status(HttpStatus.OK).body(prozivodDto);
            }
            else return new ResponseEntity("Takav proizvod ne postoji!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/searchedNFiltered")
    public ResponseEntity<Page<ProizvodDto>> getProizvodiFiltrirani(@RequestParam int strana,
                                                                    @RequestBody SearchDto parametriPretrage,
                                                                    HttpSession sesija) {
        Korisnik korisnik = (Korisnik) sesija.getAttribute("korisnik");
        if(korisnik == null){
            Page<ProizvodDto> strProizvoda = proizvodService.proizvodiSearchOrFilter(parametriPretrage, strana);
            if (strProizvoda == null) {
                return new ResponseEntity("Filteri nisu dobro primenjeni", HttpStatus.BAD_REQUEST);
            }
            if (strProizvoda.isEmpty()) {
                return new ResponseEntity("Nema proizvoda kojji ispunjavaju te uslove!", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(strProizvoda);
        } else if(korisnik.getUloga() != Korisnik.TipKorisnika.Administrator){
            Page<ProizvodDto> strProizvoda = proizvodService.proizvodiSearchOrFilter(parametriPretrage, strana);
            if (strProizvoda == null) {
                return new ResponseEntity("Filteri nisu dobro primenjeni", HttpStatus.BAD_REQUEST);
            }
            if (strProizvoda.isEmpty()) {
                return new ResponseEntity("Nema proizvoda kojji ispunjavaju te uslove!", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(strProizvoda);
        }
        return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/add")
    public ResponseEntity<Proizvod> postaviProizvodNaProdaju(@RequestBody ProizvodDto proizvodDto, HttpSession sesija) {
        Korisnik korisnik = (Korisnik) sesija.getAttribute("korisnik");
        if (korisnik == null) return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
        if(korisnik.getUloga() == Korisnik.TipKorisnika.Prodavac){
            Proizvod proizvod = proizvodService.postaviProizvodNaProdaju(proizvodDto);
            return ResponseEntity.ok(proizvod);
        }
        return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
    }

    /*@PostMapping("/api/purchase")
    public ResponseEntity kupovina(@RequestBody ProizvodDto proizvodDto, HttpSession sesija){
        if(sesija.getAttribute("korisnik") == null) return new ResponseEntity("Zabranjen pristup", HttpStatus.FORBIDDEN);
        Korisnik korisnik = (Korisnik) sesija.getAttribute("korisnik");

        Optional<Proizvod> proizvod = proizvodService.getProizvod(proizvodDto.getId());

        if(proizvod.isPresent() && proizvod.get().isProdat() == false){
            if(proizvod.get().getTipProdaje() == Proizvod.tipprodaje.fiksnaCena){

            }
        } else return new ResponseEntity("Prozivod je prodat ili ne postoji!", HttpStatus.NOT_FOUND);

    }*/

}
