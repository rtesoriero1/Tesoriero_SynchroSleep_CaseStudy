package com.memory.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.memory.demo.model.Profile;
import com.memory.demo.model.User;
import com.memory.demo.repository.ProfileRepository;
import com.memory.demo.service.ProfileService;


public class ProfileServiceTest {

	ProfileService profileService;
	
	@Mock 
	ProfileRepository profileRepository;
	
	@BeforeEach
	public void prep() {
		
		
		profileService = new ProfileService ();
		profileService.setProfileRepository(profileRepository);
	}
	
	
	
	
	@Test
	public void testGetProfileByUserId() {
		Profile testProfile = new Profile();
		testProfile.setPId((long) 4);
		
		User testUser = new User();
		testUser.setId((long) 9);
		
		List<Profile> expectedProfiles = new ArrayList<>();
		expectedProfiles.add(testProfile);
		
		when(profileRepository.getAllProfilesByUser(testUser.getId())).thenReturn(expectedProfiles);
		
		
		//When
		List<Profile> realProfiles = profileService.getAllProfilesByUser(testUser.getId());
		
		
		//Then
		assertEquals(expectedProfiles, realProfiles);
		
		
	}
}
