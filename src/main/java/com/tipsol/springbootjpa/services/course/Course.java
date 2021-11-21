package com.tipsol.springbootjpa.services.course;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@NamedQueries(value = { 
		@NamedQuery(name = "query_get_all_courses", 
				query = "Select  c  From Course c"),
		@NamedQuery(name = "query_get_100_Step_courses", 
		query = "Select  c  From Course c where name like '%100 Steps'") })
@Data
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@SQLDelete(sql = "update course set is_deleted = true where id = ?")
@Where(clause = "is_deleted=false")
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	
	private boolean isDeleted;
	
	@ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<>();

	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<>();
	
	@UpdateTimestamp
	@JsonIgnore
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	@JsonIgnore
	private LocalDateTime createdDate;

	protected Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	@PreRemove
	public void preDelete() {
		this.isDeleted = true;
	}

	
}