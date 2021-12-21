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
@Table(name="STD_SECTION")
@Data
public class SectionInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String secCode;
	
	@ManyToOne
	private Quote quote;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "section")
	private List<RiskInfo> risks;
}
