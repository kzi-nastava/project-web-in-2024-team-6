package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Recenzija implements Serializable {

    @Column
    private int ocena;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String komentar;

    @Column(name = "datum_recenzije")
    private LocalDate datumRecenzije;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

}
