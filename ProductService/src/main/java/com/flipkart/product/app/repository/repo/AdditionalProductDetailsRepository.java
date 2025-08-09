package com.flipkart.product.app.repository.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.product.app.repository.entity.ProductAdditionalDetails;

public interface AdditionalProductDetailsRepository extends JpaRepository<ProductAdditionalDetails, Long>{
	
}
