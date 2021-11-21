package com.tipsol.springbootjpa.services.course;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

	
	@Autowired
	EntityManager em;

	public Review findById(Long id) {
		return em.find(Review.class, id);
	}

	public Review save(Review review) {

		if (review.getId() == null) {
			em.persist(review);
		} else {
			em.merge(review);
		}

		return review;
	}

	public void deleteById(Long id) {
		Review review = findById(id);
		em.remove(review);
	}

}
