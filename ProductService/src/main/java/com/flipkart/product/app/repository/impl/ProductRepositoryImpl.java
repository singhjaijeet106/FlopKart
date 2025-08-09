package com.flipkart.product.app.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.flipkart.product.app.constant.ProductStatus;
import com.flipkart.product.app.exception.ProductNotFoundException;
import com.flipkart.product.app.helper.ProductHelper;
import com.flipkart.product.app.repository.ProductRepository;
import com.flipkart.product.app.repository.entity.ProductDetails;
import com.flipkart.product.app.repository.repo.ProductDetailRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final ProductHelper productHelper;
	private final ProductDetailRepository productDetailRepository;

	public ProductRepositoryImpl(ProductHelper productHelper, ProductDetailRepository productDetailRepository) {
		this.productHelper = productHelper;
		this.productDetailRepository = productDetailRepository;
	}

	@Override
	public ProductDetails createProduct(ProductDetails details) {
		if (details != null) {
			details.setProductId(productHelper.generateProductId(details.getCategory()));
			this.productDetailRepository.save(details);
		}
		return details;
	}

	@Override
	public List<ProductDetails> getAllProducts() {
		return this.productDetailRepository.findAll();
	}

	@Override
	public ProductDetails getProductById(String productId) {
		if (productId != null && !productId.trim().isEmpty()) {
			return this.productDetailRepository.findByProductId(productId);
		}
		return null;
	}

	@Override
	public ProductDetails updateProduct(String productId, ProductDetails productDetails) {

		ProductDetails existingProduct = productDetailRepository.findByProductId(productId);

		if (existingProduct != null) {

			if (productDetails.getProductName() != null) {
				existingProduct.setProductName(productDetails.getProductName());
			}

			if (productDetails.getProductDescription() != null) {
				existingProduct.setProductDescription(productDetails.getProductDescription());
			}

			if (productDetails.getPrice() != 0) { // assuming 0 means "not set", change if 0 is valid price
				existingProduct.setPrice(productDetails.getPrice());
			}

			if (productDetails.getCategory() != null) {
				existingProduct.setCategory(productDetails.getCategory());
			}

			if (productDetails.getAdditionalDetails() != null) {
				existingProduct.getAdditionalDetails().clear();
				existingProduct.getAdditionalDetails().addAll(productDetails.getAdditionalDetails());
			}

			existingProduct.setUpdatedAt(LocalDateTime.now());

			return productDetailRepository.save(existingProduct);

		} else {
			throw new ProductNotFoundException("Product with productId ['" + productId + "'] is not found");
		}
	}

	@Override
	public ProductDetails deleteProduct(String productId) {
		ProductDetails existingProduct = productDetailRepository.findByProductId(productId);

		if (existingProduct != null) {
			existingProduct.setStatus(ProductStatus.DELETED);
			existingProduct.setUpdatedAt(LocalDateTime.now());

			return productDetailRepository.save(existingProduct);
		} else {
			throw new ProductNotFoundException("Product with productId ['" + productId + "'] is not found");
		}
	}
	
	@Override
	public List<ProductDetails> createProductBulk(List<ProductDetails> products) {
		 products.forEach(p -> {
	            p.setProductId(productHelper.generateProductId(p.getCategory()));
	        });
		 
		return productDetailRepository.saveAll(products);
	}

}
