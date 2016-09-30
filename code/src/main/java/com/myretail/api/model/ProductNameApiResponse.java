package com.myretail.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductNameApiResponse {

    private ProductCompositeResponse product_composite_response;

    public ProductNameApiResponse() {
    }

    public ProductCompositeResponse getProduct_composite_response() {
        return product_composite_response;
    }

    public void setProduct_composite_response(ProductCompositeResponse product_composite_response) {
        this.product_composite_response = product_composite_response;
    }

    @Override
    public String toString() {
        return "ProductNameApiResponse{" +
                "product_composite_response=" + product_composite_response +
                '}';
    }
}
