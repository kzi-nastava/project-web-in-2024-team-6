package com.webshop.controller;

import com.webshop.dto.ProizvodDto;

import com.webshop.dto.SearchDto;
import com.webshop.model.Kategorija;


import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import jakarta.persistence.EntityNotFoundException;
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


    @PostMapping("/krajAukcije/{id}")
    public ResponseEntity<ProizvodDto> proglasiKrajAukcije(@PathVariable("id") Long id, HttpSession session) {
        if (session.getAttribute("korisnik") == null) {
           Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
           if(korisnik.getUloga() == Korisnik.TipKorisnika.Prodavac) {
               try {
                   Proizvod proizvod = proizvodService.proglasiKrajAukcije(id);
                   ProizvodDto proizvodDto = new ProizvodDto(
                           proizvod.getNaziv(),
                           proizvod.getOpis(),
                           proizvod.getKategorija(),
                           proizvod.getCena(),
                           proizvod.getSlika(),
                           proizvod.getTipProdaje()
                   );
                   return ResponseEntity.status(HttpStatus.OK).body(proizvodDto);
               } catch (IllegalArgumentException e) {
                   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
               } catch (Exception e) {
                   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
               }
           }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProizvodDto> azurirajProizvod(@PathVariable("id") Long id, @RequestBody ProizvodDto azuriraniProizvod, HttpSession session) {
        if (session.getAttribute("korisnik") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try {
            Proizvod updatedProizvod = proizvodService.azurirajProizvod(id, azuriraniProizvod);
            ProizvodDto proizvodDto = new ProizvodDto(
                    updatedProizvod.getNaziv(),
                    updatedProizvod.getOpis(),
                    updatedProizvod.getKategorija(),
                    updatedProizvod.getCena(),
                    updatedProizvod.getSlika(),
                    updatedProizvod.getTipProdaje()
            );
            return ResponseEntity.status(HttpStatus.OK).body(proizvodDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
