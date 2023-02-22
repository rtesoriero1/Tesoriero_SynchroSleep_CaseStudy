package com.memory.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.memory.demo.model.Dream;

import com.memory.demo.service.DreamService;
//This controls all aspects related to the user's dream journal. 
@Controller
public class DreamController {

	//Second example of injection for managed Bean
@Autowired
private DreamService dreamService;

//Displays the Dream Journal page, and loads the table of previous dreams, prepares a new instance of Dream object for the form.
@GetMapping("/dream")
public String showDreamJournal(Model model) {
	//Used to list previous Dreams
	List<Dream> dreams = dreamService.getAllDreams();
	model.addAttribute("dreams", dreams);
	
	//Prepping new Dream for Dream Journal forum
	Dream dream = new Dream();
	model.addAttribute("dream", dream);
	
	return "dream";
}

//Used to save the submitted dream to to the database, and redirects to /dream to update the table.
@PostMapping ("/dream/save")
public String saveDream(@ModelAttribute("night") Dream dream,BindingResult result,Model model) {
	dreamService.addNight(dream);
	
	return "redirect:/dream";
}
}
