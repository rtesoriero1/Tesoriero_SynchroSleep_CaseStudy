package com.tesoriero.synchrosleep.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tesoriero.synchrosleep.model.Night;
import com.tesoriero.synchrosleep.model.Profile;
import com.tesoriero.synchrosleep.service.NightService;
import com.tesoriero.synchrosleep.service.ProfileService;

//This controller controls everything related to the Night POJO, this mainly includes using the attributes from a User's Profile to calculate their bed time.

@Controller
public class NightController {

	
@Autowired
private NightService nightService;

@Autowired
private ProfileService profileService;

//We are making sure to use @PathVariable of the previous profile so that their attributes can be used later on this page.
	@GetMapping("/night/{pId}")
	public String showNightCalculator (@PathVariable( "pId") Long pId,  Model model) {
		Optional<Profile> profile =	profileService.getProfileById(pId);
		Profile _profile = profile.get();
		Night night = new Night();
		model.addAttribute("profile", _profile);
		model.addAttribute("night", night);
		return "night"; 
		
		//giving me nWake
	}
	
	//Here we are calculating the client's bedtime. 
	@PostMapping("/night/save/{pId}")
	public String saveNight(@ModelAttribute("night") Night night, @RequestParam( "pId") Long pId,  BindingResult result,Model model) {
		
	//We get the profile's attributes through the method here
	Optional<Profile> profile =	profileService.getProfileById(pId);
		Profile _profile = profile.get();
		
		//save nWake to the DB 
		nightService.addNight(night);
		//use nWake to help generate nBed 
		night.setNBed(nightService.nightCalc(_profile, night, night.getNWake()));
		
		
		//We need to get this String so that the results page knows which Night to get the bedtime from.
		String nId = String.valueOf(night.getNId());
		
		///save to the DB with nBed 
		nightService.addNight(night);
		
		//I attach the nId string to the redirect so that its in the url and the results display for the Night with that Id
		return "redirect:/bedtime/" + nId;
	}
	
	//This end point shows a new page that displays the client's bed time. We need to get the id for that night we just saved to the DB.
	@GetMapping("/bedtime/{nId}")
	public String showBedTime (@PathVariable("nId") Long nId, Model model) {
		//Used to load in the information of that specific Night 
		Optional<Night> night = nightService.getNightById(nId);
		
		//Using model Attribute to display the bedtime in the html page. 
		Night night_ = night.get();
		model.addAttribute("night", night_);
		
		
		return "bedtime";
	}
}
