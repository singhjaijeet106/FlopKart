package com.flipkart.product.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.product.app.constant.ProductStatus;
import com.flipkart.product.app.model.ProductModel;
import com.flipkart.product.app.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
		ProductModel createdProduct = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@GetMapping
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		List<ProductModel> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductModel> getProductById(@PathVariable String productId) {
		ProductModel product = productService.getProductById(productId);
		return ResponseEntity.ok(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductModel> updateProduct(@PathVariable String id, @RequestBody ProductModel productDetails) {
		ProductModel updatedProduct = productService.updateProduct(id, productDetails);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable String id) {
		ProductStatus status = productService.deleteProduct(id);
		return (status == ProductStatus.DELETED)? ResponseEntity.ok("Product deleted successfully"):ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Product deleted successfully") ;		
	}
	
	@PostMapping("/bulk-create")
    public ResponseEntity<Map<ProductModel, Boolean>> createProductsBulk(@RequestBody List<ProductModel> products) {
        Map<ProductModel, Boolean> result = productService.createProductBulk(products);
        return ResponseEntity.ok(result);
    }
}