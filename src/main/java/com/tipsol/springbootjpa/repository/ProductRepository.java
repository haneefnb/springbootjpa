package com.tipsol.springbootjpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tipsol.springbootjpa.dao.ProductDao;

@Repository
public interface ProductRepository extends CrudRepository<ProductDao, Long>{
	Optional<ProductDao> findByName(String name);
}
