package com.myretail.api.dao;

import com.myretail.api.config.SpringMongoConfig;
import com.myretail.api.model.Item;
import com.myretail.api.model.MongoPrice;
import com.myretail.api.model.Price;
import com.myretail.api.model.ProductInfo;
import com.myretail.api.model.ProductNameApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Repository("ProductInfoDao")
public class ProductInfoDaoImpl implements ProductInfoDao {

  @Autowired
  MongoOperations mongoOperation;

  public ProductInfoDaoImpl() {
  }

  @Override
	public ProductInfo getProductInfo(long id) {
    // get description from API
    RestTemplate restTemplate = new RestTemplate();
    ProductNameApiResponse productNameApiResponse = restTemplate.getForObject("https://api.target.com/products/v3/" + id + "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz"
      , ProductNameApiResponse.class);

    // get price from mongo
    Price returnProductPrice = getPriceFromMongo(id);

    // populate return object
    ProductInfo returnProductInfo = new ProductInfo();
    returnProductInfo.setId(id);
    Item firstItem = productNameApiResponse.getProduct_composite_response().getItems().get(0);
    if(firstItem.getErrors() != null && firstItem.getErrors().size() > 0) {
      returnProductInfo.setName(firstItem.getErrors().get(0).getMessage());
    } else {
      returnProductInfo.setName(firstItem.getOnline_description().getValue());
    }
    returnProductInfo.setCurrent_price(returnProductPrice);

    return returnProductInfo;
  }

  private Price getPriceFromMongo(long id) {
    MongoPrice mongoPrice = mongoOperation.findById(id, MongoPrice.class);
    Price returnProductPrice = null;
    if(mongoPrice != null) {
      returnProductPrice = new Price();
      returnProductPrice.setValue(mongoPrice.getValue());
      returnProductPrice.setCurrency_code(mongoPrice.getCurrency_code());
    }
    return returnProductPrice;
  }

}
