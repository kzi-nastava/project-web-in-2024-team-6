package com.webshop.controller;
import com.webshop.model.PrijavaProfila;
import com.webshop.service.PrijavaProfilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prijave")
public class PrijavaProfilaRestController {

    @Autowired
    private PrijavaProfilaService prijavaService;

    @GetMapping
    public ResponseEntity<List<PrijavaProfila>> getAllPrijave() {
        List<PrijavaProfila> prijave = prijavaService.getAllPrijave();
        return ResponseEntity.ok(prijave);
    }

    @PutMapping("/{prijavaId}/odbij")
    public ResponseEntity<String> odbijPrijavu(@PathVariable Long prijavaId, @RequestParam String razlogOdbijanja) {
        prijavaService.odbijPrijavu(prijavaId, razlogOdbijanja);
        return ResponseEntity.ok("Prijava odbijena.");
    }

    @PutMapping("/{prijavaId}/prihvati")
    public ResponseEntity<String> prihvatiPrijavu(@PathVariable Long prijavaId) {
        prijavaService.prihvatiPrijavu(prijavaId);
        return ResponseEntity.ok("Prijava prihvaćena.");
    }
}