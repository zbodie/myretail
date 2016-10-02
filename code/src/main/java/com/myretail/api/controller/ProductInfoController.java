package com.myretail.api.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.myretail.api.dao.ProductInfoDao;
import com.myretail.api.model.ProductInfo;
import com.myretail.api.model.Price;

@RestController
public class ProductInfoController {

  @Autowired
  ProductInfoDao productInfoDao;

  @RequestMapping(value="/product/{id}", method=RequestMethod.GET)
  public ProductInfo getProductInfo(@PathVariable("id") int id) {
    ProductInfo productInfo = new ProductInfo();
    productInfo = productInfoDao.getProductInfo(id);
    return productInfo;
  }

  @RequestMapping(value="/product/{id}", method=RequestMethod.PUT)
  public ProductInfo putProductInfo(@PathVariable("id") int id, @RequestBody ProductInfo newProductInfo) {
    ProductInfo productInfo = new ProductInfo();
    productInfo = productInfoDao.setProductInfo(newProductInfo);
    return productInfo;
  }
}
