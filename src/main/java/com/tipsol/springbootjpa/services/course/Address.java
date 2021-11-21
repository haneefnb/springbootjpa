package com.tipsol.springbootjpa.services.course;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Address {
	protected Address() {}

	private String line1;
	private String line2;
	private String city;

	
}
