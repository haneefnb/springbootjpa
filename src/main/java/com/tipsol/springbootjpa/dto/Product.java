package com.tipsol.springbootjpa.dto;

import java.time.LocalDate;

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
	
}
