package com.flipkart.product.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flipkart.product.app.constant.ProductStatus;
import com.flipkart.product.app.exception.ProductNotFoundException;
import com.flipkart.product.app.exception.ProductValidationException;
import com.flipkart.product.app.helper.ProductHelper;
import com.flipkart.product.app.model.ProductModel;
import com.flipkart.product.app.repository.ProductRepository;
import com.flipkart.product.app.repository.entity.ProductDetails;
import com.flipkart.product.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductHelper productHelper;

	public ProductServiceImpl(ProductRepository productRepository, ProductHelper productHelper) {
		this.productHelper = productHelper;
		this.productRepository = productRepository;
	}

	@Override
	public ProductModel createProduct(ProductModel product) {
		// validate the ProductModel
		if (!productHelper.isProductModelValid(product,true)) {
			throw new ProductValidationException("Invalid Product details");
		}
		ProductDetails details = productHelper.convertToProductDetails(product);
		details = productRepository.createProduct(details);
		product = productHelper.convertToProductModel(details);
		return product;
	}

	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductModel> productList = null;
		List<ProductDetails> productDetailsList = productRepository.getAllProducts();
		if (productDetailsList != null && !productDetailsList.isEmpty()) {
			productList = productDetailsList.stream().map(productHelper::convertToProductModel).toList();
		}
		return productList;
	}

	@Override
	public ProductModel getProductById(String productId) {
		if (productId == null || productId.isEmpty()) {
			throw new ProductValidationException("Invalid Product Id");
		}
		ProductDetails details = productRepository.getProductById(productId);
		if (details == null) {
			throw new ProductNotFoundException("Product with product ID [" + productId + "] not found");
		}
		return productHelper.convertToProductModel(details);

	}

	@Override
	public ProductModel updateProduct(String productId, ProductModel productModel) {
		if (productId == null || productId.isEmpty()) {
			throw new ProductValidationException("Invalid Product Id");
		}
		ProductDetails productDetails = productHelper.convertToProductDetails(productModel);
		productDetails = productRepository.updateProduct(productId, productDetails);
		return productHelper.convertToProductModel(productDetails);
	}

	@Override
	public ProductStatus deleteProduct(String productId) {
		if (productId == null || productId.isEmpty()) {
			throw new ProductValidationException("Invalid Product Id");
		}
		ProductDetails productDetails = productRepository.deleteProduct(productId);
		if (productDetails != null) {
			return ProductStatus.DELETED;
		}
		return ProductStatus.FAILED;
	}

	@Override
	public Map<ProductModel, Boolean> createProductBulk(List<ProductModel> products) {
		Map<ProductModel, Boolean> result = new HashMap<>();

		Map<Boolean, List<ProductModel>> partitioned = products.stream()
				.collect(Collectors.partitioningBy(p->productHelper.isProductModelValid(p, true)));
	
		List<ProductDetails> productDetails = partitioned.get(true).stream().map(productHelper::convertToProductDetails).toList();
		List<ProductModel> invalidProduct = partitioned.get(false);
		List<ProductModel> validProduct = productRepository.createProductBulk(productDetails).stream().map(productHelper::convertToProductModel).toList();
		
		

		// Put valid products with value true
		validProduct.forEach(p -> result.put(p, true));
		// Put invalid products with value false
		invalidProduct.forEach(p -> result.put(p, false));
		
		return result;
	}

}
