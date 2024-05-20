package com.webshop.service;

import com.webshop.dto.ProizvodDto;
import com.webshop.dto.SearchDto;
import com.webshop.model.Proizvod;
import com.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProizvodService {

    @Autowired
    private ProizvodRepository proizvodRepository;

    public Page<ProizvodDto> getProizvodi(int brstr, int velstr) {

        Pageable stranica = PageRequest.of(brstr, velstr);

        Page<ProizvodDto> proizvodi = proizvodRepository.findAllPageable(stranica);

        return proizvodi;

    }

    public Optional<Proizvod> getProizvod(Long id) {

        return proizvodRepository.findById(id);

    }

    public Page<ProizvodDto> proizvodiSearchOrFilter(SearchDto filteri, int brstr){
        String flagStr = filteri.getFlagovi();
        Pageable str = PageRequest.of(brstr, 10);
        switch (flagStr){
            case "00000"://32
                Page<ProizvodDto> proizvodi = proizvodRepository.findAllPageable(str);
                return proizvodi;
            case "00001"://31
                Page<ProizvodDto> strProizvodaPoTipiProdaje = proizvodRepository.findByTipProdaje(filteri.getTipP(), str);
                return strProizvodaPoTipiProdaje;
            case "00010"://30
                Page<ProizvodDto> strProizvodaPoKategoriji = proizvodRepository.findByKategorija(filteri.getKategorija(), str);
                return strProizvodaPoKategoriji;
            case "00011"://29
                Page<ProizvodDto> strProizvodaPoTipuProdajeIPoKategoriji = proizvodRepository.findByTipProdajeAndKategorija(filteri.getTipP(), filteri.getKategorija(), str);
                return strProizvodaPoTipuProdajeIPoKategoriji;
            case "00100"://28
                Page<ProizvodDto> strProizvodaCenaManjaOd= proizvodRepository.findByCenaLeCenaDo(filteri.getCenado(), str);
                return strProizvodaCenaManjaOd;
            case "00101"://27
                Page<ProizvodDto> strProizvodaCenaManjaOdIPoTipuProdaje= proizvodRepository.findByCenaLeCenaDoAndTipProdaje(filteri.getCenado(), filteri.getTipP(), str);
                return strProizvodaCenaManjaOdIPoTipuProdaje;
            case "00110"://26
                Page<ProizvodDto> strProizvodaCenaManjaOdIPoKategoriji= proizvodRepository.findByCenaLeCenaDoAndKategorija(filteri.getCenado(), filteri.getKategorija(), str);
                return strProizvodaCenaManjaOdIPoKategoriji;//------------
            case "00111"://25
                Page<ProizvodDto> strProizvodaCenaManjaOdIPoKategorijiIPoTipuProdaje= proizvodRepository.findByCenaLeCenaDoAndKategorijaAndTipProdaje(filteri.getCenado(), filteri.getKategorija(), filteri.getTipP(), str);
                return strProizvodaCenaManjaOdIPoKategorijiIPoTipuProdaje;
            case "01000"://24
                Page<ProizvodDto> strProizvodaCenaVecaOd= proizvodRepository.findByCenaGeCenaOd(filteri.getCenaod(), str);
                return strProizvodaCenaVecaOd;
            case "01001"://23
                Page<ProizvodDto> strProizvodaCenaVecaOdITipProdaje= proizvodRepository.findByCenaGeCenaOdAndTipPRodaje(filteri.getCenaod(), filteri.getTipP(), str);
                return strProizvodaCenaVecaOdITipProdaje;
            case "01010"://22
                Page<ProizvodDto> strProizvodaCenaVecaOdIKategorija= proizvodRepository.findByCenaGeCenaOdAndKategorija(filteri.getCenaod(), filteri.getKategorija(), str);
                return strProizvodaCenaVecaOdIKategorija;
            case "01011"://21
                Page<ProizvodDto> strProizvodaCenaVecaOdIKategorijaITipProdaje= proizvodRepository.findByCenaGeCenaOdAndKategorijaAndTipProdaje(filteri.getCenaod(), filteri.getKategorija(), filteri.getTipP(), str);
                return strProizvodaCenaVecaOdIKategorijaITipProdaje;
            case "01100"://20
                Page<ProizvodDto> strProizvodaCenaIzmedju= proizvodRepository.findByCenaBetween(filteri.getCenaod(), filteri.getCenado(), str);
                return strProizvodaCenaIzmedju;
            case "01101"://19
                Page<ProizvodDto> strProizvodaCenaIzmedjuITipProdaje = proizvodRepository.findByCenaBetweenAndTipProdaje(filteri.getCenaod(), filteri.getCenado(), filteri.getTipP(), str);
                return strProizvodaCenaIzmedjuITipProdaje;
            case "01110"://18
                Page<ProizvodDto> strProizvodaCenaIzmedjuIKategorija = proizvodRepository.findByCenaBetweenAndKategorija(filteri.getCenaod(), filteri.getCenado(), filteri.getKategorija(), str);
                return strProizvodaCenaIzmedjuIKategorija;
            case "01111"://17
                Page<ProizvodDto> strProizvodaCenaIzmedjuIKategorijaITipProdaje = proizvodRepository.findByCenaBetweenAndKategorijaAndTipProdaje(filteri.getCenaod(), filteri.getCenado(), filteri.getKategorija(), filteri.getTipP(), str);
                return strProizvodaCenaIzmedjuIKategorijaITipProdaje;
            case "10000"://16
                Page<ProizvodDto> strProizvodaNazivIOpis = proizvodRepository.findByNazivOrOpis(filteri.getImeopis(), str);
                return strProizvodaNazivIOpis;
            case "10001"://15
                Page<ProizvodDto> strProizvodaNazivIOpisITipProdaje = proizvodRepository.findByNazivOrOpisAndTipProdaje(filteri.getImeopis(), filteri.getTipP(), str);
                return strProizvodaNazivIOpisITipProdaje;
            case "10010"://14
                Page<ProizvodDto> strProizvodaNazivIOpisIKategorija = proizvodRepository.findByNazivOrOpisAndKategorija(filteri.getImeopis(), filteri.getKategorija(), str);
                return strProizvodaNazivIOpisIKategorija;
            case "10011"://13
                Page<ProizvodDto> strProizvodaNazivIOpisIKategorijaITipProdaje = proizvodRepository.findByNazivOrOpisAndKategorijaAndTipProdaje(filteri.getImeopis(), filteri.getKategorija(), filteri.getTipP(), str);
                return strProizvodaNazivIOpisIKategorijaITipProdaje;
            case "10100"://12
                Page<ProizvodDto> strProizvodaNazivIOpisICenaLeCenaDo = proizvodRepository.findByNazivOrOpisAndLtCenaDo(filteri.getImeopis(), filteri.getCenado(), str);
                return strProizvodaNazivIOpisICenaLeCenaDo;
            case "10101"://11
                Page<ProizvodDto> strProizvodaNazivIOpisICenaLeCenaDoAndTipProdaje = proizvodRepository.findByNazivOrOpisAndLtCenaDoAndTipProdaje(filteri.getImeopis(), filteri.getCenado(), filteri.getTipP(), str);
                return strProizvodaNazivIOpisICenaLeCenaDoAndTipProdaje;
            case "10110"://10
                Page<ProizvodDto> strProizvodaNazivIOpisICenaLeCenaDoAndKategorija = proizvodRepository.findByNazivOrOpisAndLtCenaDoAndKategorija(filteri.getImeopis(), filteri.getCenado(), filteri.getKategorija(), str);
                return strProizvodaNazivIOpisICenaLeCenaDoAndKategorija;
            case "10111"://9
                Page<ProizvodDto> strProizvodaNazivIOpisICenaLeCenaDoAndKategorijaAndTipProdaje = proizvodRepository.findByNazivOrOpisAndLtCenaDoAndKategorijaAndTipProdaje(filteri.getImeopis(), filteri.getCenado(), filteri.getKategorija(), filteri.getTipP(), str);
                return strProizvodaNazivIOpisICenaLeCenaDoAndKategorijaAndTipProdaje;
            case "11000"://8
                Page<ProizvodDto> strProizvodaNazivIOpisICenaGeCenaOd = proizvodRepository.findByNazivOrOpisAndGtCenaOd(filteri.getImeopis(), filteri.getCenaod(), str);
                return strProizvodaNazivIOpisICenaGeCenaOd;
            case "11001"://7
                Page<ProizvodDto> strProizvodaNazivIOpisICenaGeCenaOdITipProdaje = proizvodRepository.findByNazivOrOpisAndGtCenaOdAndTipProdaje(filteri.getImeopis(), filteri.getCenaod(), filteri.getTipP(), str);
                return strProizvodaNazivIOpisICenaGeCenaOdITipProdaje;
            case "11010"://6
                Page<ProizvodDto> strProizvodaNazivIOpisICenaGeCenaOdIKategorija = proizvodRepository.findByNazivOrOpisAndGtCenaOdAndKategorija(filteri.getImeopis(), filteri.getCenaod(), filteri.getKategorija(),  str);
                return strProizvodaNazivIOpisICenaGeCenaOdIKategorija;
            case "11011"://5
                Page<ProizvodDto> strProizvodaNazivIOpisICenaGeCenaOdIKategorijaITipProdaje = proizvodRepository.findByNazivOrOpisAndGtCenaOdAndKategorijaAndTipProdaje(filteri.getImeopis(), filteri.getCenaod(), filteri.getKategorija(), filteri.getTipP(),  str);
                return strProizvodaNazivIOpisICenaGeCenaOdIKategorijaITipProdaje;
            case "11100"://4
                Page<ProizvodDto> strProizvodaNazivIOpisICenaIzmedju = proizvodRepository.findByNazivOrOpisAndCenaBetween(filteri.getImeopis(), filteri.getCenaod(), filteri.getCenado(),  str);
                return strProizvodaNazivIOpisICenaIzmedju;
            case "11101"://3
                Page<ProizvodDto> strProizvodaNazivIOpisICenaIzmedjuAndTipProdaje = proizvodRepository.findByNazivOrOpisAndCenaBetweenAndTipProdaje(filteri.getImeopis(), filteri.getCenaod(), filteri.getCenado(), filteri.getTipP(),  str);
                return strProizvodaNazivIOpisICenaIzmedjuAndTipProdaje;
            case "11110"://2
                Page<ProizvodDto> strProizvodaNazivIOpisICenaIzmedjuAndKategorija = proizvodRepository.findByNazivOrOpisAndCenaBetweenAndKategorija(filteri.getImeopis(), filteri.getCenaod(), filteri.getCenado(), filteri.getKategorija(),  str);
                return strProizvodaNazivIOpisICenaIzmedjuAndKategorija;
            case "11111"://1
                Page<ProizvodDto> strProizvodaNazivIOpisICenaIzmedjuAndKategorijaAndTipProdaje = proizvodRepository.findByNazivOrOpisAndCenaBetweenAndKategorijaAndTipProdaje(filteri.getImeopis(), filteri.getCenaod(), filteri.getCenado(), filteri.getKategorija(), filteri.getTipP(),  str);
                return strProizvodaNazivIOpisICenaIzmedjuAndKategorijaAndTipProdaje;
            default:
                return null;
        }

    }

}
