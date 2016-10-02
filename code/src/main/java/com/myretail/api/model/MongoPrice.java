package com.myretail.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Document(collection = "price")
public class MongoPrice {

  @Id
  private int id;
  private Double value;
  private String currency_code;

  public MongoPrice() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
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
              "}";
  }
}
