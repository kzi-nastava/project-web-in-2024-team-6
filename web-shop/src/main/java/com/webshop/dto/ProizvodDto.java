package com.webshop.dto;


import com.webshop.model.Proizvod;

public class ProductDto {

    private String naziv;

    private String opis;

    private String kategorija;

    private double cena;

    private String slika;

    private Proizvod.tipprodaje tipProdaje;

    public ProductDto() { }

    public ProductDto(String naziv, String opis, String kategorija, double cena, String slika, Proizvod.tipprodaje tipProdaje) {
        this.naziv = naziv;
        this.opis = opis;
        this.kategorija = kategorija;
        this.cena = cena;
        this.slika = slika;
        this.tipProdaje = tipProdaje;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public String getKategorija() {
        return kategorija;
    }

    public double getCena() {
        return cena;
    }

    public String getSlika() {
        return slika;
    }

    public Proizvod.tipprodaje getTipProdaje() {
        return tipProdaje;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public void setTipProdaje(Proizvod.tipprodaje tipProdaje) {
        this.tipProdaje = tipProdaje;
    }
}
