package com.tipsol.springbootjpa.services.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="STD_CUSTOMER")
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String custCode;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	@OneToOne
	private Quote quote;
}
