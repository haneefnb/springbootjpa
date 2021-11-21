package com.tipsol.springbootjpa.services.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tipsol.springbootjpa.customvalidations.ValidPrefix;

import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Passport {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@ValidPrefix
	private String number;
	
	@OneToOne(mappedBy = "passport")
	//@JsonBackReference
	private Student student;

	public Passport(String number) {
		this.number = number;
	}
	public Passport() {
	}
}
