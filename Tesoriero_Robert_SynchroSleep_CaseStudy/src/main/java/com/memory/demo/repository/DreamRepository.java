package com.memory.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.memory.demo.model.Dream;
//This repository allows me to use JpaRepository functions for my Dream Services, like saving a Dream to the database. 
@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {

}
