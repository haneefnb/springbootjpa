package com.tipsol.springbootjpa.services.course;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {

	
	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {

		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}

		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public List<Course> jpql_fetch_courses_without_students(){
		TypedQuery<Course> tQuery = 
				em.createQuery("select c from Course c where c.students is empty", Course.class);
		return tQuery.getResultList();
	}
	
	public List<Course> jpql_fetch_courses_without_at_least_two_students(){
		TypedQuery<Course> tQuery = 
				em.createQuery("select c from Course c where size(c.students) >= 2 ", Course.class);
		return tQuery.getResultList();
	}
	
	public List<Course> criteria_fetch_all_courses(){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		TypedQuery<Course> tQuery = em.createQuery(cq.select(courseRoot));
		
		return tQuery.getResultList();
	}
	
	

}
