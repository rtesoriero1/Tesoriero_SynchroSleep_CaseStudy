package com.memory.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.memory.demo.model.Night;

//Used to use methods from JpaRepository for Night Services, like saving a Night to the database. Custom query to find Optional<Night> by Id. 
@Repository
public interface NightRepository extends JpaRepository<Night, Long>{

	Optional<Night>findById (Long nId);
	
}
