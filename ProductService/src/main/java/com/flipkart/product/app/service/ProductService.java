package com.flipkart.product.app.service;

import java.util.List;
import java.util.Map;

import com.flipkart.product.app.constant.ProductStatus;
import com.flipkart.product.app.model.ProductModel;

public interface ProductService {
	ProductModel createProduct( ProductModel product);
	List<ProductModel> getAllProducts();
	ProductModel getProductById(String productId);
	ProductModel updateProduct(String id,ProductModel productDetails);
	ProductStatus deleteProduct(String id);
	Map<ProductModel, Boolean> createProductBulk(List<ProductModel> products);
}
