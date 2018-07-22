package com.heso.careo.models;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalesOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String tumbnailImageUrl;
    private Element tumbnailHtml;
    private Document contentHtml;
    private String title;

    public SalesOffer(String url){
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Document getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(Document content) {
        this.contentHtml = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTumbnailImageUrl() {
        return tumbnailImageUrl;
    }

    public void setTumbnailImageUrl(String tumbnailImageUrl) {
        this.tumbnailImageUrl = tumbnailImageUrl;
    }

    public Element getTumbnailHtml() {
        return tumbnailHtml;
    }

    public void setTumbnailHtml(Element tumbnailHtml) {
        this.tumbnailHtml = tumbnailHtml;
    }

    @Override
    public String toString() {
        return "SalesOffer{" +
                "\n\tid=" + id +
                "\n,\ttitle='" + title + '\'' +
                "\n,\turl='" + url + '\'' +
                "\n,\ttumbnailImageUrl='" + tumbnailImageUrl + '\'' +
//                "\n,\tcontent='" + content + '\'' +
                "\n}";
    }
}
