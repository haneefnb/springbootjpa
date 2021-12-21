package com.tipsol.springbootjpa.services.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="STD_RISK")
public class RiskInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String make;
	private String model;
	private Double value;
	private String engineNumber;
	
	@ManyToOne
	private SectionInfo section;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "risk")
	private List<Driver> drivers;
}
