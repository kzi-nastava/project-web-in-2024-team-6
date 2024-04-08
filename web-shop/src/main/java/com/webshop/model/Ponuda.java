package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Ponuda implements Serializable {
    @Column
    private double Cena;
    @Id
    private Long id;
    @JoinColumn(name = "kupac_id") @OneToOne
    private Korisnik kupacKojiJeDaoPonudu;
    @ManyToOne
    private Proizvod proizvod;

}
