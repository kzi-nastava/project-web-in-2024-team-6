package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
public class Proizvod implements Serializable {

    public enum tipprodaje {aukcija, fiksnaCena};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String naziv;

    @Column
    private String opis;

    @Column
    private String slika;

    @OneToMany(mappedBy = "proizvod")
    private Set<Kategorija> kategorija;

    @Column
    private double cena;

    @Column(name = "datum_objavljivanja")
    private Date datumObjavljivanja;

    @Column(name = "tip_prodaje") @Enumerated
    private tipprodaje tipProdaje;

    @Column @OneToMany
    private Set<Ponuda> ponude;

    @JoinColumn(name = "prodavac_id") @ManyToOne
    private Korisnik prodavac;

    @Column(name = "ostavljenja_recenzija_od_strane_kupca")
    private boolean ostavljenaRecenzijaOdStraneKupca;

    @Column(name = "ostavljenja_recenzija_od_strane_prodavca")
    private boolean ostavljenaRecenzijaOdStraneProdavca;

    @Column
    private boolean prodat;

}
