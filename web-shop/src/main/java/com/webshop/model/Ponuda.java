package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Ponuda implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double Cena;

    @JoinColumn(name = "kupac_id") @OneToOne
    private Korisnik kupacKojiJeDaoPonudu;

    public double getCena() {
        return Cena;
    }

    public Long getId() {
        return id;
    }

    public Korisnik getKupacKojiJeDaoPonudu() {
        return kupacKojiJeDaoPonudu;
    }

    public void setCena(double cena) {
        Cena = cena;
    }

    public void setKupacKojiJeDaoPonudu(Korisnik kupacKojiJeDaoPonudu) {
        this.kupacKojiJeDaoPonudu = kupacKojiJeDaoPonudu;
    }
}
