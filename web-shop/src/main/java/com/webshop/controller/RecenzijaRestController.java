package com.webshop.controller;

import com.webshop.model.Recenzija;
import com.webshop.service.RecenzijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/recenzije")
public class RecenzijaRestController {

    @Autowired
    private RecenzijaService recenzijaService;

    @GetMapping
    public ResponseEntity<List<Recenzija>> vratiSveRecenzije() {
        List<Recenzija> reviews = recenzijaService.vratiSveRecenzije();
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> izmeniRecenziju(@PathVariable Long reviewId, @RequestBody String newComment) {
        recenzijaService.izmeniRecenziju(reviewId, newComment);
        return ResponseEntity.ok("Review updated successfully.");
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> izbrisiRecenziju(@PathVariable Long reviewId) {
        recenzijaService.izbrisiRecenziju(reviewId);
        return ResponseEntity.ok("Review deleted successfully.");
    }
}

