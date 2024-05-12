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

    @JoinColumn(name = "korisnik_podneo_prijavu_id") @ManyToOne
    private Korisnik korisnikKojiJePodneoPrijavu;

    @JoinColumn(name = "korisnik_prijavljen_id") @ManyToOne
    private Korisnik korisnikNaKogaSeOdnosiPrijavu;

    public enum statPrijave {podneta, prihvacena, odbijena};

    @Column @Enumerated
    private statPrijave statusPrijave;

    public long getId() {
        return id;
    }

    public String getRazlogPrijave() {
        return razlogPrijave;
    }

    public Date getDatumPodnosenjaPrijave() {
        return datumPodnosenjaPrijave;
    }

    public Korisnik getKorisnikKojiJePodneoPrijavu() {
        return korisnikKojiJePodneoPrijavu;
    }

    public Korisnik getKorisnikNaKogaSeOdnosiPrijavu() {
        return korisnikNaKogaSeOdnosiPrijavu;
    }

    public statPrijave getStatusPrijave() {
        return statusPrijave;
    }

    public void setRazlogPrijave(String razlogPrijave) {
        this.razlogPrijave = razlogPrijave;
    }

    public void setDatumPodnosenjaPrijave(Date datumPodnosenjaPrijave) {
        this.datumPodnosenjaPrijave = datumPodnosenjaPrijave;
    }

    public void setKorisnikKojiJePodneoPrijavu(Korisnik korisnikKojiJePodneoPrijavu) {
        this.korisnikKojiJePodneoPrijavu = korisnikKojiJePodneoPrijavu;
    }

    public void setKorisnikNaKogaSeOdnosiPrijavu(Korisnik korisnikNaKogaSeOdnosiPrijavu) {
        this.korisnikNaKogaSeOdnosiPrijavu = korisnikNaKogaSeOdnosiPrijavu;
    }

    public void setStatusPrijave(statPrijave statusPrijave) {
        this.statusPrijave = statusPrijave;
    }
}
