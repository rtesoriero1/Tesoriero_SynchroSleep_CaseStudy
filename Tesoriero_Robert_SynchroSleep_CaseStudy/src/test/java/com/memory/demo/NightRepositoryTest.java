package com.memory.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tesoriero.synchrosleep.model.Night;
import com.tesoriero.synchrosleep.model.User;
import com.tesoriero.synchrosleep.repository.NightRepository;

@ExtendWith(MockitoExtension.class)
public class NightRepositoryTest {

	
	@Mock
	NightRepository nightRepository; 
	
	@Test
	public void testFindNightById() {
		Optional<Night> night = Optional.of(new Night());
		Night night_ = night.get();
		Long nId = (long) 1; 
		night_.setNId(nId);;
		
		when(nightRepository.findById(nId)).thenReturn(night);
		
		//when
		//Optional incase it comes back null
		Optional<Night> result = nightRepository.findById(nId);
		Night result_ = result.get();
		//then 
		assertEquals(result_.getNId(), nId);
		
		
	}
}
