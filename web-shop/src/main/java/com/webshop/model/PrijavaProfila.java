package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class PrijavaProfila implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String razlogPrijave;

    @Column
    private Date datumPodnosenjaPrijave;

    @JoinColumn(name = "korisnik_podneo_prijavu_id") @OneToOne
    private Korisnik korisnikKojiJePodneoPrijavu;

    @JoinColumn(name = "korisnik_prijavljen_id") @OneToOne
    private Korisnik korisnikNaKogaSeOdnosiPrijavu;

    public enum statPrijave {podneta, prihvacena, odbijena};

    @Column @Enumerated
    private statPrijave statusPrijave;

}
