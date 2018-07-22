package com.heso.careo.controllers;

import com.heso.careo.controllers.SalesOfferController;
import com.heso.careo.models.SalesOffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search_results")
public class SearchResultParser {

    private Document doc;

    @Autowired
    SalesOfferController salesOfferController;

    public String getTitle(){
        return doc.title();
    }

    public List<SalesOffer> getOffers(){
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
        Elements offers = doc.select(offerListSelector);

        List<SalesOffer> salesOffers = new ArrayList<>();

        for (Element offer: offers){
            Elements offerLink = offer.select("a.offer-item__photo-link[href]");
            SalesOfferParser salesOfferParser = new SalesOfferParser();

            if (salesOfferParser.parseUrl(offerLink.attr("href"))){
                SalesOffer salesOffer = salesOfferParser.getSalesOffer();
                salesOffer.setTumbnailImageUrl(offerLink.attr("data-bg"));
                salesOffer.setTitle(offerLink.attr("title"));
                salesOffer.setTumbnailHtml(offer);

                salesOffers.add(salesOffer);
            }
        }


        return salesOffers;
    }
}
