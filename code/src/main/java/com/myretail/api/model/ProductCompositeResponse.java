package com.myretail.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCompositeResponse {

  private List<RequestAttribute> request_attributes;
  private List<Item> items;

  public ProductCompositeResponse() {
  }

  public List<RequestAttribute> getRequest_attributes() {
    return request_attributes;
  }

  public void setRequest_attributes(List<RequestAttribute> request_attributes) {
    this.request_attributes = request_attributes;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "ProductCompositeResponse{" +
            "request_attributes=" + request_attributes +
            ", items=" + items +
            "}";
  }
}
