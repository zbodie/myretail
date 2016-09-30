package com.myretail.api.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.myretail.api.model.ProductInfo;
import com.myretail.api.model.Price;

@RestController
public class ProductInfoController {

    @RequestMapping(value="/product/{id}", method=RequestMethod.GET)
    public ProductInfo productInfo(@PathVariable("id") Long id) {
        ProductInfo productInfo = new ProductInfo();
        Price productPrice = new Price();
        productPrice.setValue(13.49);
        productPrice.setCurrency_code("USD");
        productInfo.setId(id);
        productInfo.setCurrent_price(productPrice);
        productInfo.setName("The Big Lebowski (Blu-ray)");
        return productInfo;
    }
}
