package com.tipsol.springbootjpa.services.product;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductDao, Long>{
	Optional<ProductDao> findByName(String name);
}
