package com.memory.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.memory.demo.model.Profile;
import com.memory.demo.repository.ProfileRepository;

//Used to implement Profile Services 
@Service
public class ProfileService{

	@Autowired 
	private ProfileRepository profileRepository;
	
	
	//Uses custom query to populate Profile Hub according to the Profiles that belong to the logged in User 
	public List<Profile> getAllProfilesByUser(@Param("user_id")Long pId) {
		return profileRepository.getAllProfilesByUser(pId);
	}
	
	//Custom query that deletes all Profiles related to the logged in User 
	List<Profile> deleteAllProfilesByUser(@Param("user_id")Long pId) {
		return profileRepository.deleteAllProfilesByUser(pId);
	}
	
	//Used to populate a table with all profiles 
	public List<Profile> getAllProfiles() {
		   List<Profile> profiles = new ArrayList<Profile>();
		   profileRepository.findAll().forEach(profiles::add);
	       return profiles;
	}
	 
	//Used to get a specific profile by ID, used when doing Bed Time calculations and updating profiles 
	 public Optional<Profile> getProfileById(Long pId){
		 return profileRepository.findById(pId);
	 }
	 
	 //Used to add Profile to Database 
	 public void addProfile (Profile profile)
	 {
		 profileRepository.save(profile);
	 }
	 
	 //Used to update a specific profile 
	 public void updateProfile (Long pId, Profile profile)
	 {
		 //Find the profile in question by Id 
		 Optional<Profile> profileData = profileRepository.findById(pId);
		 
		 //If a profile by that Id is found, update it through the information entered in form and then save it to the DB
	       if (profileData.isPresent()) {
	          Profile targetProfile = profileData.get();
	        targetProfile.setPName(profile.getPName());
	        targetProfile.setPAge(profile.getPAge());
	        targetProfile.setPActivity(profile.getPActivity());
	        targetProfile.setPSex(profile.getPSex());
	        targetProfile.setPWeight(profile.getPWeight());
	           addProfile(targetProfile);
	       }
		
	 }
	 
	 //Allows a Profile to be deleted by specific idea, used for the Delete button in the profile hub 
	 public void deleteProfile (Long pId) {
		 profileRepository.deleteById(pId);
	 }

	 //Used when testing 
	public void setProfileRepository(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
		
	}
	 
	

	
}
