package com.webshop.service;

import com.webshop.dto.ProizvodDto;
import com.webshop.model.Kategorija;
import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import com.webshop.repository.KategorijaRepository;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProizvodService {

    @Autowired
    private ProizvodRepository proizvodRepository;

    public Page<ProizvodDto> getProizvodi(int brstr, int velstr) {

        Pageable stranica = PageRequest.of(brstr, velstr);

        Page<Proizvod> proizvodi = proizvodRepository.findAll(stranica);

        List<ProizvodDto> spremniProizvodi = new ArrayList<>();

        for(Proizvod i : proizvodi) {
            ProizvodDto dto = new ProizvodDto(i.getNaziv(),
                                              i.getOpis(),
                                              i.getKategorija(),
                                              i.getCena(),
                                              i.getSlika(),
                                              i.getTipProdaje());
            spremniProizvodi.add(dto);
        }

        return new PageImpl<>(spremniProizvodi, stranica, proizvodi.getTotalElements());
    }

    @Autowired
    private KategorijaRepository kategorijaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Proizvod postaviProizvodNaProdaju(ProizvodDto proizvodDto) {
        Kategorija kategorija = proizvodDto.getKategorija();
        if (kategorija == null) {
            kategorija = new Kategorija();
            kategorija.setNaziv(proizvodDto.getKategorija().getNaziv());
            kategorijaRepository.save(kategorija);
        }

        Korisnik prodavac = korisnikRepository.findById(proizvodDto.getProdavacId()).orElseThrow(() ->
                new IllegalArgumentException("Prodavac sa ID-jem " + proizvodDto.getProdavacId() + " ne postoji."));

        Proizvod proizvod = new Proizvod();
        proizvod.setNaziv(proizvodDto.getNaziv());
        proizvod.setOpis(proizvodDto.getOpis());
        proizvod.setSlika(proizvodDto.getSlika());
        proizvod.setCena(proizvodDto.getCena());
        proizvod.setKategorija(kategorija);
        proizvod.setTipProdaje(proizvodDto.getTipProdaje());
        proizvod.setProdavac(prodavac);
        proizvod.setDatumObjavljivanja(new Date());

        kategorijaRepository.save(kategorija);
        proizvodRepository.save(proizvod);
        prodavac.getPrizvodi().add(proizvod);
        korisnikRepository.save(prodavac);

        return proizvod;
    }

}
