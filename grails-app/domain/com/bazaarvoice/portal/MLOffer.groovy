package com.bazaarvoice.portal

class MLOffer {

    static constraints = {
    }

     int offerId;
     int merchantId;
     String merchantName;
     String merchantLogo;
     String merchantProductUrl;
     int priceSource;
     float price;
     String prettyPrice;
     float salePrice;
     String prettySalePrice;
     float currentPrice;
     String prettyCurrentPrice;
     List<MLVariation> variations;

    float getSmallestCurrentPrice() {

    }

    String getSmallestPrettyCurrentPrice() {

    }
}
