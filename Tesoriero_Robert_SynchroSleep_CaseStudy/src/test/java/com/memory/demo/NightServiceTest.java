package com.memory.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tesoriero.synchrosleep.model.Night;
import com.tesoriero.synchrosleep.model.Profile;
import com.tesoriero.synchrosleep.repository.NightRepository;
import com.tesoriero.synchrosleep.service.NightService;

@ExtendWith(MockitoExtension.class)
public class NightServiceTest {

	NightService nightService;
	
	@Mock
	NightRepository nightRepository;
	
	@BeforeEach
	public void prep() {
		MockitoAnnotations.openMocks(this);
		
		nightService = new NightService();
		nightService.setNightRepository(nightRepository);
	}
	
	
	
	@Test
	public void testNightCalc() {
		
		
		Profile profile = new Profile();
		Night night = new Night ();
	
		
		profile.setPName("Mr. Test" );
		profile.setPAge(32);
		profile.setPSex("Male");
		profile.setPWeight(145);
		profile.setPActivity("Average");
		
		night.setNWake("08:00");
		night.setProfile(profile);
		
		String testResult = nightService.nightCalc(profile, night, night.getNWake());
	
		//when
		
		String actualResult = nightService.nightCalc(profile, night, night.getNWake());
		System.out.println(actualResult);
		
		//then
		assertEquals(testResult, actualResult);
	}
}
