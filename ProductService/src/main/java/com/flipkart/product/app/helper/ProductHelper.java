package com.flipkart.product.app.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.flipkart.product.app.constant.ProductCategory;
import com.flipkart.product.app.model.AdditionalDetailsModel;
import com.flipkart.product.app.model.ProductModel;
import com.flipkart.product.app.repository.entity.ProductAdditionalDetails;
import com.flipkart.product.app.repository.entity.ProductDetails;

@Component
public class ProductHelper {

	private static final Random RANDOM = new Random();

	public ProductDetails convertToProductDetails(ProductModel productModel) {

		ProductDetails productDetails = ProductDetails.builder().productId(productModel.getProductId())
				.productName(productModel.getProductName()).productDescription(productModel.getProductDescription())
				.price(productModel.getPrice()).status(productModel.getStatus()).category(productModel.getCategory())
				.createdAt(productModel.getCreatedAt()).updatedAt(productModel.getUpdatedAt()).build();

		if (productModel.getAdditionalDetails() != null && productModel.getAdditionalDetails().size() > 0) {
			List<ProductAdditionalDetails> additionalDetails = productModel.getAdditionalDetails().stream()
					.map(this::convertToProductAdditionalDetails).toList();
			productDetails.setAdditionalDetails(additionalDetails);
		}

		return productDetails;
	}

	public ProductModel convertToProductModel(ProductDetails productDetails) {

		ProductModel productModel = ProductModel.builder().productId(productDetails.getProductId())
				.productName(productDetails.getProductName()).productDescription(productDetails.getProductDescription())
				.price(productDetails.getPrice()).category(productDetails.getCategory())
				.status(productDetails.getStatus()).createdAt(productDetails.getCreatedAt())
				.updatedAt(productDetails.getUpdatedAt()).build();

		if (productDetails.getAdditionalDetails() != null && !productDetails.getAdditionalDetails().isEmpty()) {
			List<AdditionalDetailsModel> additionalDetailsModels = productDetails.getAdditionalDetails().stream()
					.map(this::convertToAdditionalDetailsModel).toList();
			productModel.setAdditionalDetails(additionalDetailsModels);
		}

		return productModel;
	}

	public ProductAdditionalDetails convertToProductAdditionalDetails(AdditionalDetailsModel additionalDetailsModel) {

		ProductAdditionalDetails additionalDetails = ProductAdditionalDetails.builder()
				.name(additionalDetailsModel.getName()).value(additionalDetailsModel.getValue())
				.createdAt(additionalDetailsModel.getCreatedAt()).updatedAt(additionalDetailsModel.getUpdatedAt())
				.build();

		return additionalDetails;
	}

	public AdditionalDetailsModel convertToAdditionalDetailsModel(ProductAdditionalDetails productAdditionalDetails) {

		AdditionalDetailsModel additionalDetails = AdditionalDetailsModel.builder()
				.name(productAdditionalDetails.getName()).value(productAdditionalDetails.getValue())
				.createdAt(productAdditionalDetails.getCreatedAt()).updatedAt(productAdditionalDetails.getUpdatedAt())
				.build();

		return additionalDetails;
	}

	public boolean isProductModelValid(ProductModel productModel, boolean skipStatusCheck) {
	    if (productModel == null) {
	        System.out.println("Validation failed: productModel is null");
	        return false;
	    }
	    if (productModel.getProductName() == null || productModel.getProductName().trim().isEmpty()) {
	        System.out.println("Validation failed: productName is null or empty");
	        return false;
	    }
	    if (productModel.getPrice() <= 0) {
	        System.out.println("Validation failed: price is less than or equal to zero");
	        return false;
	    }
	    if (productModel.getCategory() == null) {
	        System.out.println("Validation failed: category is null");
	        return false;
	    }
	    if (productModel.getStatus() == null && !skipStatusCheck) {
	        System.out.println("Validation failed: status is null");
	        return false;
	    }
	    return true;
	}


	public String generateProductId(ProductCategory category) {
		String categoryCode = category.name().substring(0, Math.min(4, category.name().length()));
		String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String randomString = getRandomAlphaNumeric(6);
		int randomPart = RANDOM.nextInt(90000) + 10000;
		return categoryCode + "-" + datePart + "-" + randomString + "-" + randomPart;
	}

	private String getRandomAlphaNumeric(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(characters.charAt(RANDOM.nextInt(characters.length())));
		}
		return sb.toString();
	}
}
