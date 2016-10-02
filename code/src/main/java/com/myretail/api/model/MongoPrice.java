package com.myretail.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "price")
public class MongoPrice {

  @Id
  private long id;
  private Double value;
  private String currency_code;

  public MongoPrice() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
              "id=" + id +
              ", value=" + value +
              ", currency_code=" + currency_code +
              '}';
  }
}
