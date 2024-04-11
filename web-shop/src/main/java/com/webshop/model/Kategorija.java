package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kategorija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String naziv;

    @OneToMany(mappedBy = "kategorija")
    private Set<Proizvod> proizvod = new HashSet<>();


    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setProizvod(Set<Proizvod> proizvod) {
        this.proizvod = proizvod;
    }

    public long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public Set<Proizvod> getProizvod() {
        return proizvod;
    }
}
