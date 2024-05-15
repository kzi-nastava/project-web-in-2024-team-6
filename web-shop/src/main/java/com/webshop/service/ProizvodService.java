package com.webshop.service;

import com.webshop.dto.ProizvodDto;
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

}
