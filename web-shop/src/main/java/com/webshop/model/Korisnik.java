package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum TipKorisnika {Prodavac, Kupac, Administrator};

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column(unique = true, name = "korisnicko_ime")
    private String korisnickoIme;

    @Column(unique = true, name = "mejl_adresa")
    private String mejlAdresa;

    @Column(unique = true, name = "broj_telefona")
    private String brojTelefona;

    @Column(nullable = false)
    private String lozinka;

    @Column(name = "datum_rodjenja")
    private Date datumRodjenja;

    @Column(name = "putanja_do_slike")
    private String putanjaDoSlike;

    @Column
    private String opis;

    @Column(nullable = false)
    private boolean blokiran;

    @OneToMany
    private ArrayList<Proizvod> kupljeniPrizvodi = new ArrayList<>();

    @Column
    private double prosenaOcena;


    @Column(name = "tip_korisnika") @Enumerated
    private TipKorisnika tipKorisnika;


    public static String encrypt(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ SECRET_KEY);
        }
        return new String(chars);
    }


    @Column(name = "uloga") @Enumerated
    private TipKorisnika uloga;


}
