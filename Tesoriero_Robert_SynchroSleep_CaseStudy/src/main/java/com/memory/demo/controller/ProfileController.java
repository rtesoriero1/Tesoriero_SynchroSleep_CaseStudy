package com.memory.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.memory.demo.model.Night;
import com.memory.demo.model.Profile;
import com.memory.demo.repository.ProfileRepository;
import com.memory.demo.service.NightService;
import com.memory.demo.service.ProfileService;
import com.memory.demo.service.UserService;



//Profile ocntroller handles creating your first profile, generating new ones, updating them, deleting them, selecting them and viewing the profile hub

@Controller
public class ProfileController {

	
	@Autowired
	UserService userService; 
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	NightService nightService;
	
//Used for creating the first profile after registering an account
@GetMapping("/create")
public String getProfileCreationPage(Model model) {
	Profile profile = new Profile();
	model.addAttribute("profile", profile);
	
	return "create_profile";
}

//Saves this inital profile to the database 
@PostMapping("/create/save")
public String saveNewProfile(@ModelAttribute("profile") Profile profile,BindingResult result,Model model) {
	profileService.addProfile(profile);
	
	return "redirect:/";
}





//Used for adding additional profiles from the Hub 
@GetMapping("/new")
public String addNewProfile(Model model) {
	Profile profile = new Profile();
	model.addAttribute("profile", profile);
	
	return "new_profile";
}

//Saves these additional profiles to the database 
@PostMapping("/new/save")
public String saveAdditionalProfile(@ModelAttribute("profile") Profile profile,BindingResult result,Model model) {
	profileService.addProfile(profile);
	
	return "redirect:/hub";
}



//This is the homepage or hub that displays all of the profiles that a user can choose from. 
@GetMapping("/hub")
public String users(Model model) {
	//Specific table through use of Custom Query and Session management? 
	List<Profile> profiles = profileService.getAllProfiles();
	model.addAttribute("profiles", profiles);
	return "hub";
}


//This handles selecting a profile and sending the user to that specific profile's page which contains relevant information and profile specific options
@GetMapping("/profile/{pId}")
public String showProfile(@PathVariable("pId") Long pId, Model model ) {
	//Used to load in the information of that specific profile 
	Optional<Profile> profile = profileService.getProfileById(pId);
	Profile profile_ = profile.get();
	model.addAttribute("profile", profile_);
	
	//Used to load list of Nights already calculated. 
	List<Night> nights = nightService.getAllNights();
	model.addAttribute("nights", nights);
	
	
	return "profile_page";
	
}

//This handles the deleting of a profile from the hub 
@GetMapping("/hub/delete/{pId}")
public String deleteProfile(@PathVariable("pId") Long pId) {
	
	profileService.deleteProfile(pId);
	return "redirect:/hub";
}

//This handles the updating of a profile from the hub
@GetMapping("/hub/update/{pId}")
public String updateProfile(@PathVariable("pId") Long pId, Model model ) {
	
	//Getting the profile by Id and taking that to an alternate "create profile" page where the info is used for updates
	Optional<Profile> profile = profileService.getProfileById(pId);
	model.addAttribute("profile", profile);
	model.addAttribute("use", "Update User");
	return "new_profile";
}



}
