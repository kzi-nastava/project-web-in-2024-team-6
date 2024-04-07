package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Kategorija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String naziv;

    @ManyToOne
    private Proizvod proizvod;

}
