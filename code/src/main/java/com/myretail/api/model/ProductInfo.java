package com.myretail.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo {

    private Long id;
    private String name;
    private Price current_price;

    public ProductInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Price current_price) {
        this.current_price = current_price;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", name=" + name +
                ", current_price=" + current_price +
                '}';
    }
}
