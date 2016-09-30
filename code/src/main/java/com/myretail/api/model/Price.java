package com.myretail.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {

    private Double value;
    private String currency_code;

    public Price() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                ", currency_code=" + currency_code +
                '}';
    }
}
