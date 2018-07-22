package com.heso.careo.controllers;

import com.heso.careo.models.SalesOffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SalesOfferParser {
    private SalesOffer salesOffer;

    public boolean parseUrl(String url){
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(3000)
                    .get();
            salesOffer = new SalesOffer(url);
            salesOffer.setContentHtml(doc);
            return true;
        }
        catch (IOException ioException){
            System.out.println("IO F'ed up.");
            return false;
        }
    }

    public SalesOffer getSalesOffer() {
        return salesOffer;
    }

    public void setSalesOffer(SalesOffer salesOffer) {
        this.salesOffer = salesOffer;
    }
}
