package com.tesoriero.synchrosleep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tesoriero.synchrosleep.model.BankInfo;

//Bank Repository that extends into JpaRepository to use methods required to save information to database, contains a custom Optional findById method
@Repository
public interface BankRepository extends JpaRepository<BankInfo, Long> {
	
	Optional<BankInfo> findById (Long cId);

}
