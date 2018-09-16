package com.noobsquad.farmshare.testfiles;


public class CardItem {

    private String crop;
    private String price;

    public CardItem(String crop, String price) {
       this.crop = crop;
       this.price = price;
    }

    public String getCrop(){return crop;}

    public String getPrice(){return price;}
}
