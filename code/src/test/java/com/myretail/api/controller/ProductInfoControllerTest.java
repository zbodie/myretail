package com.myretail.api.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.myretail.api.model.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductInfoControllerTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getProductInfoValidProductId() throws Exception {
        long id = 13860428;
        ResponseEntity<ProductInfo> response = template.getForEntity(base.toString() + "/product/" + id, ProductInfo.class);

        ProductInfo returnedInfo = response.getBody();
        assertThat(returnedInfo.getId(), equalTo(id));
        assertThat(returnedInfo.getName(), equalTo("The Big Lebowski (Blu-ray)"));
        assertThat(returnedInfo.getCurrent_price().getValue(), equalTo(13.49));
        assertThat(returnedInfo.getCurrent_price().getCurrency_code(), equalTo("USD"));
    }

    @Test
    public void getProductInfoInvalidProductId() throws Exception {
        long id = 55;
        ResponseEntity<ProductInfo> response = template.getForEntity(base.toString() + "/product/" + id, ProductInfo.class);

        ProductInfo returnedInfo = response.getBody();
        assertThat(returnedInfo.getId(), equalTo(id));
        assertThat(returnedInfo.getName(), equalTo("Not valid product in system: This product ID does not represent a valid product"));
        assertThat(returnedInfo.getCurrent_price(), equalTo(null));
    }
}
