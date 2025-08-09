package com.flipkart.product.app.model;

import java.time.LocalDateTime;
import java.util.List;

import com.flipkart.product.app.constant.ProductCategory;
import com.flipkart.product.app.constant.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel {

	private String productId;
	private String productName;
	private String productDescription;
	private double price;
	private ProductCategory category;
	private ProductStatus status;
	private List<AdditionalDetailsModel>  additionalDetails ;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
