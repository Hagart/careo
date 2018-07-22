package com.heso.careo;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.heso.careo.models.SalesOffer;
import com.heso.careo.controllers.SearchResultParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

//@SpringBootApplication
public class CareoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(CareoApplication.class, args);

		SearchResultParser searchResultParser = new SearchResultParser();
        List<SalesOffer> offers = searchResultParser.getOffers();
        Element someOffer = offers.get(0).getTumbnailHtml();

        Elements links = someOffer.select("a[href]");


        System.out.println(offers);
    }
}