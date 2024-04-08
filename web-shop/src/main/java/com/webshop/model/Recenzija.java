package com.webshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Recenzija implements Serializable {
    @Column
    private int ocena;
    @Id
    private Long id;
    @Column
    private String komentar;
    @Column(name = "datum_recenzije")
    private LocalDate datumRecenzije;
    @ManyToOne
    @Column
    private Korisnik korisnik;

}
