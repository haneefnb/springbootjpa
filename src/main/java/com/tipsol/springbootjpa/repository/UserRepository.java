package com.tipsol.springbootjpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tipsol.springbootjpa.dao.User;
@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
}
