package com.flipkart.product.app.repository;

import java.util.List;

import com.flipkart.product.app.repository.entity.ProductDetails;

public interface ProductRepository {
	ProductDetails createProduct(ProductDetails details);
	List<ProductDetails> getAllProducts();
	ProductDetails getProductById(String productId);
	ProductDetails updateProduct(String id, ProductDetails productDetails);
	ProductDetails deleteProduct(String productId);
	List<ProductDetails> createProductBulk(List<ProductDetails> products);
}
