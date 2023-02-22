package com.memory.demo;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.memory.demo.model.Profile;
import com.memory.demo.model.User;
import com.memory.demo.repository.ProfileRepository;

@ExtendWith(MockitoExtension.class)
public class ProfileRepositoryTest {

	@Mock
	ProfileRepository profileRepository; 
	
	
	@Test
	public void testGetAllProfilesByUser() {
		User user = new User();
		user.setId((long) 1);
		Profile profile = new Profile();
		Profile profile2 = new Profile();
		Profile profile3 = new Profile();
	
	//Assigning Profiles to the user
		profile.setUser(user);
		profile2.setUser(user);
		profile3.setUser(user);
		
		profile.setPName("Test1");
		profile2.setPName("Test2");
		profile3.setPName("Test3");
		
		//Adding profiles to List for testing
		List<Profile> testProfiles = new ArrayList<>();
		testProfiles.add(profile3);
		testProfiles.add(profile2);
		testProfiles.add(profile);
		
	//Getting the user_id for function parameter 	
	Long user_id = user.getId();
	
	when(profileRepository.getAllProfilesByUser(user_id)).thenReturn(testProfiles);
	 
	//when
	List<Profile> realProfiles = profileRepository.getAllProfilesByUser(user_id);
	
	//then
	assertThat(realProfiles).isNotNull();
    assertThat(realProfiles.size()).isEqualTo(testProfiles.size());
    assertThat(realProfiles).containsAll(testProfiles);
		
	}

	
	@Test
	public void testDeleteProfilesByUser() {
		User user = new User();
		user.setId((long) 1);
		Profile profile = new Profile();
		profile.setPName("testProfile");
		
		//Getting the user_id for function parameter 	
		Long user_id = user.getId();
		
		
		//Adding profiles to List for testing
				List<Profile> testProfiles = new ArrayList<>();
				testProfiles.add(profile);
		
		when(profileRepository.deleteAllProfilesByUser(user_id)).thenReturn(testProfiles);
		
		//When
		List<Profile> actualProfiles = new ArrayList<>();
		actualProfiles.add(profile);
		actualProfiles = profileRepository.deleteAllProfilesByUser(user_id);
		
		
		//Then
		
	    assertThat(actualProfiles.size()).isEqualTo(testProfiles.size());
	    assertThat(actualProfiles).containsAll(testProfiles);
				
	}
	
	
	}
	

