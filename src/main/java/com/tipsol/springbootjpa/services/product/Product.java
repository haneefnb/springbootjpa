package com.tipsol.springbootjpa.services.product;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private Long id;
	private String name;
	private String description;
	private Double price;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate manfDate;
	
	//@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private LocalDateTime createdDate;
	
	//@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private LocalDateTime modifiedDate;
}
