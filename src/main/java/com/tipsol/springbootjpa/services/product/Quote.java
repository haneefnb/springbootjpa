package com.tipsol.springbootjpa.services.product;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name="STD_QUOTE")
public class Quote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String quoteNumber;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String prodCode;
	private String createdBy;
	private String updatedBy;
	private LocalTime created_date;
	private LocalTime updated_date;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "quote")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "quote")
	private List<SectionInfo> sections;
	
}
