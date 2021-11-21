package com.tipsol.springbootjpa.services.course;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PassportService {


	@Autowired
	EntityManager em;

	public Passport findById(Long id) {
		return em.find(Passport.class, id);
	}
	
	public Passport save(Passport passport) {

		if (passport.getId() == null) {
			em.persist(passport);
		} else {
			em.merge(passport);
		}

		return passport;
	}



}
