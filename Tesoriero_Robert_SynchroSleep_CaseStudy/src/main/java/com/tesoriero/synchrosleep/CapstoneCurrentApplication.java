package com.tesoriero.synchrosleep;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tesoriero.synchrosleep.model.Night;
import com.tesoriero.synchrosleep.model.Profile;
import com.tesoriero.synchrosleep.repository.NightRepository;
import com.tesoriero.synchrosleep.service.NightService;
import com.tesoriero.synchrosleep.service.ProfileService;


/**
 * 
 * @author Robert Tesoriero 
 * 
 * Description: 
 * SynchroSleep allows Users to register an account and create customized Sleep Profiles that can then be used to calculate optimized sleep schedules for themselves, 
 * as well as providing a journal for them to log any dreams they might have.
 *
 */
@SpringBootApplication
public class CapstoneCurrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneCurrentApplication.class, args);
		
	}

}
