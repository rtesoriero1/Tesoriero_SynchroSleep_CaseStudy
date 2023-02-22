package com.memory.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.memory.demo.model.Profile;

//Repository for Profiles that contain two custom queries. 
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	//Allows me to filter my profiles that belong to a specific user for the purpose of generating that user's profile hub. 
	@Query(value = "SELECT * FROM profiles WHERE user_id = :user_id; ", nativeQuery = true)
	List<Profile> getAllProfilesByUser(@Param("user_id")Long pId);
	
	
	//Used for the purpose of wiping a User's profile base
	@Query(value = "DELETE FROM profiles WHERE user_id = :user_id; ", nativeQuery = true)
	List<Profile> deleteAllProfilesByUser(@Param("user_id")Long pId);
	
		


}
