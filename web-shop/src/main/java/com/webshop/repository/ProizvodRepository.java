package com.webshop.repository;

import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {
}