package com.memory.demo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.memory.demo.model.Night;
import com.memory.demo.model.Profile;
import com.memory.demo.repository.NightRepository;
import com.memory.demo.service.NightService;
import com.memory.demo.service.ProfileService;

@SpringBootApplication
public class CapstoneCurrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneCurrentApplication.class, args);
		
	}

}
