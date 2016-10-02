package com.myretail.api.dao;

import com.myretail.api.model.Item;
import com.myretail.api.model.Price;
import com.myretail.api.model.ProductInfo;
import com.myretail.api.model.ProductNameApiResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;

@Repository("ProductInfoDao")
public class ProductInfoDaoImpl implements ProductInfoDao {

  @Override
	public ProductInfo getProductInfo(long id) {
    // get description from API
    RestTemplate restTemplate = new RestTemplate();
    ProductNameApiResponse productNameApiResponse = restTemplate.getForObject("https://api.target.com/products/v3/" + id + "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz"
      , ProductNameApiResponse.class);

    // TODO: get price from mongo
    Price returnProductPrice = new Price();
    returnProductPrice.setValue(13.49);
    returnProductPrice.setCurrency_code("USD");

    // populate return object
    ProductInfo returnProductInfo = new ProductInfo();
    returnProductInfo.setId(id);
    Item firstItem = productNameApiResponse.getProduct_composite_response().getItems().get(0);
    if(firstItem.getErrors() != null && firstItem.getErrors().size() > 0) {
      returnProductInfo.setName("Unknown Product");
    } else {
      returnProductInfo.setName(firstItem.getOnline_description().getValue());
    }
    returnProductInfo.setCurrent_price(returnProductPrice);

    return returnProductInfo;
  }

}
