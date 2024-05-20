package com.webshop.controller;

import com.webshop.dto.ProizvodDto;
import com.webshop.dto.SearchDto;
import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;
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
//treba pogledati da li tako ili ne
    @GetMapping
    public ResponseEntity<Page<ProizvodDto>> getProizvodi(@RequestParam int strana, @RequestParam int vel, HttpSession sesija) {
        if (sesija.getAttribute("korisnik") == null) {
            if(vel == 0) vel = 1;
            Page<ProizvodDto> strProizvoda = proizvodService.getProizvodi(strana, vel);
            if (strProizvoda.isEmpty()) {
                return new ResponseEntity("Nema proizvoda!", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(strProizvoda);
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProizvodDto> getProizvod(@PathVariable("id") Long id, HttpSession sesija) {
        if (sesija.getAttribute("korisnik") == null) {
            Optional<Proizvod> proizvod = proizvodService.getProizvod(id);
            if(proizvod.isPresent()){
                ProizvodDto prozivodDto = new ProizvodDto(proizvod.get().getNaziv(), proizvod.get().getOpis(), proizvod.get().getKategorija(), proizvod.get().getCena(), proizvod.get().getSlika(), proizvod.get().getTipProdaje());
                return ResponseEntity.status(HttpStatus.OK).body(prozivodDto);
            }
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return null;
    }

    @PostMapping("/searchedNFiltered")
    public ResponseEntity<Page<ProizvodDto>> getProizvodiFiltrirani(@RequestParam int strana,
                                                                    @RequestBody SearchDto parametriPretrage,
                                                                    HttpSession sesija) {
        if(sesija.getAttribute("korisnik") == null) {
            Page<ProizvodDto> strProizvoda = proizvodService.proizvodiSearchOrFilter(parametriPretrage, strana);
            if (strProizvoda == null) {
                return new ResponseEntity("Filteri nisu dobro primenjeni", HttpStatus.BAD_REQUEST);
            }
            if(strProizvoda.isEmpty()) {
                return new ResponseEntity("Nema proizvoda kojji ispunjavaju te uslove!", HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(strProizvoda);
        }
        return null;
    }

}
