package com.webshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
@Entity
public class Kupac extends Korisnik implements Serializable {
    @OneToMany
    private ArrayList<Proizvod> kupljeniPrizvodi = new ArrayList<>();
    @Column
    private double prosenaOcena;
}
