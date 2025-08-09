package com.flipkart.product.app.repository.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flipkart.product.app.repository.entity.ProductDetails;

public interface ProductDetailRepository extends JpaRepository<ProductDetails, Long> {
	// Find products by partial name (case-insensitive)
	@Query("SELECT p FROM ProductDetails p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<ProductDetails> findByProductNameContainingIgnoreCase(@Param("name") String name);

	// Find products within price range
	List<ProductDetails> findByPriceBetween(double minPrice, double maxPrice);

	ProductDetails findByProductId(String productId);
}
