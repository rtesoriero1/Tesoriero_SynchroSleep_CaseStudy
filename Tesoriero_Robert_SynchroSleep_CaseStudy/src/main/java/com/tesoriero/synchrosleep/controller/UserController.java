package com.tesoriero.synchrosleep.controller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.tesoriero.synchrosleep.model.User;
import com.tesoriero.synchrosleep.repository.ProfileRepository;
import com.tesoriero.synchrosleep.service.ProfileService;
import com.tesoriero.synchrosleep.service.UserService;

import jakarta.servlet.http.HttpSession;


//This User controller handles things like displaying the login page, handling user registration, handling login in function

@Controller
public class UserController {
		
	
	@Autowired
	UserService userService; 
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	ProfileRepository profileRepository;
	
	
	//Used to display the login page 
	@GetMapping("/")
	public String getHomePage() {
		return "login";
	}
	
	

	//Used to send the User to the user registration page 
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		
		//creating new instance of User to be populated by form. 
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	
	
	///Used to validate USer's login information before sending them to the profile hub 
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, Model model)  {
	   
		
		//Checking to make sure that the Email exists in the database through the findUserByEmail function
		if(userService.findUserByEmail(email) == null) {
			//Handling exceptions through error message 
			model.addAttribute("LoginError", "Wrong Email or Password");
			return"login";
		} 
		
		
		//Using findUserByEmail function to populate currentUser with relevant info
		User currentUser = userService.findUserByEmail(email);
		
		//Calling an instance of the BCryptpassword encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       
        //Getting the password from the db to compared with the entered String password from @RequestParam
        String enteredPassword = currentUser.getPassword();
        
       //Here we use a method from BCryptPasswordEncoder to compare the hashed password stored in the database with the raw string entered into the login.
        if(!encoder.matches(password, enteredPassword)) {
        	//Handling exceptions through error message
        	model.addAttribute("LoginError", "Wrong Email or Password");
			return"login";
        	
        	
        } 
	   
        return "redirect:/hub";
	
        
	}
	
	//Used to save information from the registration form into the database. 
	@PostMapping("/register")
	public String saveNewUser (@ModelAttribute("user") User user,BindingResult result,Model model) {
		

		//Need to check to make sure this User does not already exist
		User currentUser = userService.findUserByEmail(user.getEmail());
		if(currentUser != null && currentUser.getEmail() != null && !currentUser.getEmail().isEmpty()){
           
			model.addAttribute("EmailError", "There is already an Account for this Email");
			return"register";
			
        }
		
		//Error handling 
		  if(result.hasErrors()){
			  
	            model.addAttribute("user", user);
	        
		  }
	            
		  //Password encryption
		  int strength = 10;
		  
		  //This part sets the type of hash that is generated. 
          BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
          
         //Getting the password entered into the form 
		 String enteredPassword = user.getPassword();
		 
		 //Taking that entered password and using a BCrypt method to scramble it into a hash. 
          String encodedPassword = bcryptEncoder.encode(enteredPassword);
          
          //replacing that entered password with the hashed password. 
          user.setPassword(encodedPassword);
         
		//Saving the user into the database, now with the hashed password. 
		userService.addUser(user);
		return "redirect:/create";
	}
	
	
}
