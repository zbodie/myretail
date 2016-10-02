package com.myretail.api.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.myretail.api.model.Price;
import com.myretail.api.model.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductInfoControllerTest {
  private final int ID_IPAD_2 = 15117729;
  private final int ID_BIG_LEBOWSKI = 13860428;
  private final int ID_INVALID = 55;

  @LocalServerPort
  private int port;

  private URL base;

  @Autowired
  private TestRestTemplate template;

  @Before
  public void setUp() throws Exception {
    this.base = new URL("http://localhost:" + port + "/");

    int id = ID_BIG_LEBOWSKI;
    ProductInfo updatedProductInfo = new ProductInfo();
    Price newPrice = new Price();
    newPrice.setValue(13.49);
    newPrice.setCurrency_code("USD");
    updatedProductInfo.setId(id);
    updatedProductInfo.setCurrent_price(newPrice);

    template.put(base.toString() + "/product/" + id, updatedProductInfo);
  }

  @After
  public void cleanUp() throws Exception {
    int id = ID_IPAD_2;
    ProductInfo updatedProductInfo = new ProductInfo();
    Price newPrice = new Price();
    newPrice.setValue(299.98);
    newPrice.setCurrency_code("USD");
    updatedProductInfo.setId(id);
    updatedProductInfo.setCurrent_price(newPrice);

    template.put(base.toString() + "/product/" + id, updatedProductInfo);
  }

  @Test
  public void getProductInfoValidProductId() throws Exception {
    int id = ID_BIG_LEBOWSKI;
    ResponseEntity<ProductInfo> response = template.getForEntity(base.toString() + "/product/" + id, ProductInfo.class);

    ProductInfo returnedInfo = response.getBody();
    assertThat(returnedInfo.getId(), equalTo(id));
    assertThat(returnedInfo.getName(), equalTo("The Big Lebowski (Blu-ray)"));
    assertThat(returnedInfo.getCurrent_price().getValue(), equalTo(13.49));
    assertThat(returnedInfo.getCurrent_price().getCurrency_code(), equalTo("USD"));
  }

  @Test
  public void getProductInfoInvalidProductId() throws Exception {
    int id = ID_INVALID;
    ResponseEntity<ProductInfo> response = template.getForEntity(base.toString() + "/product/" + id, ProductInfo.class);

    ProductInfo returnedInfo = response.getBody();
    assertThat(returnedInfo.getId(), equalTo(id));
    assertThat(returnedInfo.getName(), equalTo("Not valid product in system: This product ID does not represent a valid product"));
    assertThat(returnedInfo.getCurrent_price(), equalTo(null));
  }

  @Test
  public void putProductInfo() throws Exception {
    int id = ID_IPAD_2;
    ProductInfo updatedProductInfo = new ProductInfo();
    Price newPrice = new Price();
    newPrice.setValue(5.00);
    newPrice.setCurrency_code("USD");
    updatedProductInfo.setId(id);
    updatedProductInfo.setCurrent_price(newPrice);

    template.put(base.toString() + "/product/" + id, updatedProductInfo);
    ResponseEntity<ProductInfo> response = template.getForEntity(base.toString() + "/product/" + id, ProductInfo.class);
    ProductInfo returnedInfo = response.getBody();

    assertThat(returnedInfo.getId(), equalTo(id));
    assertThat(returnedInfo.getName(), equalTo("Apple&reg; iPad Air 2 16GB Wi-Fi - Gold"));
    assertThat(returnedInfo.getCurrent_price().getValue(), equalTo(newPrice.getValue()));
    assertThat(returnedInfo.getCurrent_price().getCurrency_code(), equalTo(newPrice.getCurrency_code()));
  }
}
