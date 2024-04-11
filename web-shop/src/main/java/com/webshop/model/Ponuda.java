package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Ponuda implements Serializable {

    @Column
    private double Cena;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "kupac_id") @OneToOne
    private Korisnik kupacKojiJeDaoPonudu;

    @ManyToOne
    private Proizvod proizvod;

    public double getCena() {
        return Cena;
    }

    public Long getId() {
        return id;
    }

    public Korisnik getKupacKojiJeDaoPonudu() {
        return kupacKojiJeDaoPonudu;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setCena(double cena) {
        Cena = cena;
    }

    public void setKupacKojiJeDaoPonudu(Korisnik kupacKojiJeDaoPonudu) {
        this.kupacKojiJeDaoPonudu = kupacKojiJeDaoPonudu;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }
}
