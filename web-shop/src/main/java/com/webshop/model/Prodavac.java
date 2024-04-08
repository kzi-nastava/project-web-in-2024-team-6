package com.webshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
@Entity
public class Prodavac extends Korisnik implements Serializable {
    @OneToMany(mappedBy = "korisnik")
    private ArrayList<Proizvod> proizvodiNaProdaju = new ArrayList<>();
    @Column
    private double prosecnaOcena;
}
