package com.flipkart.product.app.model;

import java.time.LocalDateTime;

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
public class AdditionalDetailsModel {
	private String name;
	private String value;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
