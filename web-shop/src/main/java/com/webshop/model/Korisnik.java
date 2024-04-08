package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Korisnik implements Serializable {
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
    private String lozinka;
    @Column(nullable = false)
    private String EnkriptovanaLozinka = encrypt(lozinka);
    @Column(name = "datum_rodjenja")
    private LocalDate datumRodjenja;
    @Column(name = "putanja_do_slike")
    private String putanjaDoSlike;
    @Column
    private String opis;
    @Column(nullable = false)
    private boolean blokiran;
    @Id
    private Long id;
    private static final char SECRET_KEY = 'K';

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

    public static String decrypt(String input) {
        return encrypt(input);
    }
}
