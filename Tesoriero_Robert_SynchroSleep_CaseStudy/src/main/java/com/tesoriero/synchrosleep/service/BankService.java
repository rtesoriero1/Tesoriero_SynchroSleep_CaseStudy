package com.tesoriero.synchrosleep.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesoriero.synchrosleep.model.BankInfo;
import com.tesoriero.synchrosleep.repository.BankRepository;

//Used for BankServices 
@Service
public class BankService {

//For injections of BankRepository methods
	@Autowired	
	private BankRepository bankRepository;
	
//Used to save BankInfo to the database through JpaRepository
	public void addBankInfo(BankInfo bank) {
		bankRepository.save(bank);
	}
	
//Used to find specific Bank Info by Id 
	public Optional<BankInfo> findBankById(Long id) {
		return bankRepository.findById(id);
	}

	//Needed this for testing purposes. 
	public void setBankRepository(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
		
	}
}
