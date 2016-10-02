package com.myretail.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String general_description;
    private Description online_description;
    private Description store_description;
    private List<RequestError> errors;

    public Item() {
    }

    public String getGeneral_description() {
        return general_description;
    }

    public void setGeneral_description(String general_description) {
        this.general_description = general_description;
    }

    public Description getOnline_description() {
        return online_description;
    }

    public void setOnline_description(Description online_description) {
        this.online_description = online_description;
    }

    public Description getStore_description() {
        return store_description;
    }

    public void setStore_description(Description store_description) {
        this.store_description = store_description;
    }

    public void setErrors(List<RequestError> errors) {
        this.errors = errors;
    }

    public List<RequestError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "Item{" +
                "general_description=" + general_description +
                ", online_description=" + online_description +
                ", store_description=" + store_description +
                ", errors=" + errors +
                '}';
    }
}
