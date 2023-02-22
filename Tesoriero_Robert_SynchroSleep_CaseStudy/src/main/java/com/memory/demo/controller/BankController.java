package com.memory.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.memory.demo.model.BankInfo;

import com.memory.demo.service.BankService;

//This controls all aspects related to the user's bank information. 

@Controller
public class BankController {



private final BankService bankService;

//First example of injection for managed Bean
@Autowired
public BankController(BankService bankService) {
	this.bankService = bankService; 
}

//This end point shows the Bank Form where users can enter their Bank data, it generates a new instance of the Bank Info object for the form to populate.
@GetMapping("/bank")
public String showBankForm(Model model) {
	BankInfo bank = new BankInfo();
	model.addAttribute("bank", bank);
	
	return "bank_form";
}


//this end point saves the new Bank Info object from the form into the database. 
@PostMapping("/bank/save")
public String saveAdditionalProfile(@ModelAttribute("bank") BankInfo bank,BindingResult result,Model model) {
	bankService.addBankInfo(bank);
	
	
	return "redirect:/hub";
}


}
