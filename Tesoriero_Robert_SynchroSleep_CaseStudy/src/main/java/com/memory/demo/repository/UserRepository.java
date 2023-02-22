package com.memory.demo.repository;

//User Repository that has a custom findByEmail query, otherwise used for JpaRepository methods for UserServices 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.memory.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	
}
