package com.webshop.dto;


import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;

public class ProizvodDto {

    private String naziv;

    private String opis;

    private Kategorija kategorija;

    private double cena;

    private String slika;

    private Proizvod.tipprodaje tipProdaje;

    public ProizvodDto(String naziv, String opis, Kategorija kategorija, double cena, String slika, Proizvod.tipprodaje tipProdaje) {
        this.naziv = naziv;
        this.opis = opis;
        this.kategorija = kategorija;
        this.cena = cena;
        this.slika = slika;
        this.tipProdaje = tipProdaje;
    }

    public String getProdavacId() {
        return ProdavacId;
    }

    public void setProdavacId(String prodavacId) {
        ProdavacId = prodavacId;
    }

    private String ProdavacId;

    public ProizvodDto() { }

    public ProizvodDto(String naziv,String ProdavacId, String opis, Kategorija kategorija, double cena, String slika, Proizvod.tipprodaje tipProdaje) {
        this.naziv = naziv;
        this.opis = opis;
        this.kategorija = kategorija;
        this.cena = cena;
        this.slika = slika;
        this.tipProdaje = tipProdaje;
        this.ProdavacId = ProdavacId;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public Kategorija getKategorija() {
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

    public void setKategorija(Kategorija kategorija) {
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
