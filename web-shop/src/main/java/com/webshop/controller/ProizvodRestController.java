package com.webshop.controller;

import com.webshop.model.Proizvod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProizvodRestController {

    @GetMapping("/api/products")
    public List<Proizvod> proizvodView() {}
}
