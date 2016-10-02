package com.myretail.api.dao;

import com.myretail.api.model.ProductInfo;

public interface ProductInfoDao {

	ProductInfo getProductInfo(int id);

  ProductInfo setProductInfo(ProductInfo newProductInfo);

}
