package com.bazaarvoice.portal

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

class MiloService {

    static final String API_KEY = "buzz01p"
    static final String API_KEY_PARAM_KEY = "api_key"

    static final String PRODUCT_DATA_API_URL = "http://api.milo.com/v2/";
    static final String PRODUCT_DATA_PATH = 'products.json'
    static final String RTPAL_API_URL = "http://rtpal.api.milo.com/v2/";
    static final String RTPAL_API_PATH = 'availability.json'

// Product API
    static final String ISNS_PARAM_KEY = "isns";
    static final String UPCS_PARAM_KEY = "upcs";
    static final String QUERY_PARAM_KEY = "query";
    static final String PAGE_PARAM_KEY = "page";

// Location restrictions are not additive. zip_code > lat_lng
    static final String ZIP_CODE_PARAM_KEY = "zip_code";
    static final String LAT_LNG_PARAM_KEY = "lat_lng";

// Real Time Product Availability Lookup (RTPAL) API
    static final String BLOCK_PARAM_KEY = "block";
    static final String PARTIAL_PARAM_KEY = "partial";
    static final String OFFER_IDS_PARAM_KEY = "offer_ids";
    static final String RADIUS_PARAM_KEY = "radius";

    def getProductData(String query, float lat, float lng) {
        def params = [
                (API_KEY_PARAM_KEY): API_KEY,
                (QUERY_PARAM_KEY): query,
                (LAT_LNG_PARAM_KEY): "${lat},${lng}"
        ]

        def response = getProductDataClient().get(path: PRODUCT_DATA_PATH, query: params)

        println response.data

        response.data
    }

    def getProductAvailability(List<String> offers, float lat, float lng, int radius) {
        def params = [
                (API_KEY_PARAM_KEY): API_KEY,
                (OFFER_IDS_PARAM_KEY): offers.join(","),
                (LAT_LNG_PARAM_KEY): "${lat},${lng}",
                (RADIUS_PARAM_KEY): "${radius}",
                (BLOCK_PARAM_KEY): "false",
        ]

        def response = getProductAvailabilityClient().get(path: RTPAL_API_PATH, query: params)


        println response.data

        response.data
    }



    def getOfferIdsForProducts(List<MLProduct> products) {
        List<String> offerIds = new ArrayList<String>()
        for (MLProduct product : products) {
            for (MLOffer offer : product.offers) {
                if (offer.variations != null && offer.variations.size() > 0) {
                    for (MLVariation variation : offer.variations) {
                        String offerMap = "${offer.offerId}:${variation.variationId}"
                        offerIds.add(offerMap)
                    }
                } else {
                    offerIds.add(offer.offerId as String)
                }
            }
        }
    }

    RESTClient getProductDataClient() {
        new RESTClient(PRODUCT_DATA_API_URL, ContentType.JSON)
    }

    RESTClient getProductAvailabilityClient() {
        new RESTClient(RTPAL_API_URL, ContentType.JSON)
    }

}
