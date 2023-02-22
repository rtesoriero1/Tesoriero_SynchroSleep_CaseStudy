package com.memory.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.memory.demo.model.BankInfo;
import com.memory.demo.repository.BankRepository;
import com.memory.demo.service.BankService;

public class BankServiceTest {

	BankService bankService;
	
	@Mock
	BankRepository bankRepository;
	
	
	@BeforeEach
	public void prep() {
		MockitoAnnotations.openMocks(this);
		
		bankService = new BankService();
		bankService.setBankRepository(bankRepository);
		
	}
	
	
	@Test
	public void testGetBankById() {
		
		Optional<BankInfo> testBank = Optional.of(new BankInfo());
		BankInfo bank_ = testBank.get();
		bank_.setCId((long) 4);

		when(bankRepository.findById(bank_.getCId())).thenReturn(testBank);
		
		//when
		
		Optional<BankInfo> actualBank = bankService.findBankById(bank_.getCId());
		BankInfo actualBank_ = actualBank.get();
		//then 
		assertEquals(actualBank_.getCId(), bank_.getCId());
		
		
	}
}
