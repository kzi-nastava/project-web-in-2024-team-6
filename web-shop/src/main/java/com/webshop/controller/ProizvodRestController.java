package com.webshop.controller;

import com.webshop.dto.ProizvodDto;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProizvodRestController {

    @Autowired
    private ProizvodService proizvodService;

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
        return ResponseEntity.ok(proizvodService.getProizvodi(strana, vel));
    }
}
