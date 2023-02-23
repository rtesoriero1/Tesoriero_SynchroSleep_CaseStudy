package com.memory.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tesoriero.synchrosleep.model.BankInfo;
import com.tesoriero.synchrosleep.model.Night;
import com.tesoriero.synchrosleep.repository.BankRepository;
import com.tesoriero.synchrosleep.repository.NightRepository;

@ExtendWith(MockitoExtension.class)
public class BankRepositoryTest {

	@Mock
	BankRepository bankRepository; 
	
	@Test
	public void testFindBandById() {
		Optional<BankInfo> bank = Optional.of(new BankInfo());
		BankInfo bank_ = bank.get();
		Long bId = (long) 1; 
		bank_.setCId(bId);

		when(bankRepository.findById(bId)).thenReturn(bank);
		
		//when
		//Optional incase it comes back null
		Optional<BankInfo> result = bankRepository.findById(bId);
		BankInfo result_ = result.get();
		//then 
		assertEquals(result_.getCId(), bId);
		
		
	}	
	
	
	
}
