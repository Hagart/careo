package com.heso.careo.models;

import com.heso.careo.controllers.SalesOfferController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/search_results")
public class SearchResultParser {

    private Document doc;

    @Autowired
    SalesOfferController salesOfferController;

    public String getTitle(){
        return doc.title();
    }

    public Elements getOffers(){
        try {
            this.doc = Jsoup.connect("https://www.otomoto.pl/osobowe/audi/a5/f5-2016/")
                    .userAgent("Mozilla")
                    .timeout(3000)
                    .get();
        }
        catch (IOException ioException){
            System.out.println("IO F'ed up.");
        }
        String offerListSelector = "#body-container > div.container-fluid.container-fluid-sm > div:nth-child(1) > div > div.om-list-container > div.offers.list > article";
        // TODO: 7/21/2018 handle pagination
        return doc.select(offerListSelector);
    }

    @GetMapping
    public void saveAnOffer(){
        Elements offers = getOffers();
        SalesOffer salesOffer = new SalesOffer(offers.first().html());
        salesOfferController.create(salesOffer);
    }

}
