package com.heso.careo.controllers;

import com.heso.careo.models.SalesOffer;
import com.heso.careo.repositories.SalesOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/api/v1/sales_offers")
public class SalesOfferController {
    @Autowired
    private SalesOfferRepository salesOfferRepository;

    @GetMapping
    public List<SalesOffer> list(){
        return salesOfferRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(SalesOffer salesOffer){
        salesOfferRepository.save(salesOffer);
    }

    @GetMapping("/{id}")
    public SalesOffer get(@PathVariable("id") long id){
        return salesOfferRepository.getOne(id);
    }
}
