package com.tesoriero.synchrosleep.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesoriero.synchrosleep.model.Night;
import com.tesoriero.synchrosleep.model.Profile;
import com.tesoriero.synchrosleep.repository.NightRepository;

//Used to implement Night services 
@Service
public class NightService {

	@Autowired
	private NightRepository nightRepository;

	// This is used to save nights to the Repository
	public void addNight(Night night) {
		nightRepository.save(night);

	}

	// This is used to get a specific night, used for calculating recommended Bed
	// Time
	public Optional<Night> getNightById(Long nId) {
		return nightRepository.findById(nId);
	}

	// This is used when building a history of nights
	public List<Night> getAllNights() {
		List<Night> nights = new ArrayList<Night>();
		nightRepository.findAll().forEach(nights::add);
		return nights;
	}

	// This is the logic for how the recommended Bed Time is calculated. It takes
	// into account traits from profile, and the wake up time from Night
	public String nightCalc(Profile profile, Night night, String wakeUp) {

		// converting time from Night Forum from String to Time
		// DateTimeFormatter parser = DateTimeFormatter.ofPattern("h[:mm]a");
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime localTime = LocalTime.parse(wakeUp, parser);

		// Getting the variables that will be used to calculate how much sleep the
		// client needs.

		// All of these are calculated in minutes
		long requiredSleep = 0;
		long fallAsleep = 0;
		long totalSleepTimeMinutes = 0;

		// Initializing the String where the client will be told what time they need to
		// go to bed.
		String goToBed = null;

		// Getting relevant information from the User's profile.
		String Gender = profile.getPSex();
		int Age = profile.getPAge();
		int Weight = profile.getPWeight();
		String Activity = profile.getPActivity();

		// If statements that assist with the ranges of data obtained from the profile
		// that will effect sleep needs.

		// The variables Activity Level and Weight are used to estimate how long it will
		// take the client to fall asleep
		if ("Low".equals(Activity)) {
			fallAsleep = fallAsleep + 20;
		} else if ("Average".equals(Activity)) {

			fallAsleep = fallAsleep + 10;

		}

		if (Weight < 175) {

			fallAsleep = fallAsleep + 20;

		} else if (Weight < 140) {
			fallAsleep = fallAsleep + 10;
		}

		// The variables of gender and age from the profile are used to calculate the
		// total sleep required per night in minutes
		if ("Female".equals(Gender)) {

			requiredSleep = requiredSleep + 20;

		}

		if (Age < 13) {

			if (fallAsleep >= 30) {
				requiredSleep = requiredSleep + 660;

			} else if (fallAsleep >= 20) {
				requiredSleep = requiredSleep + 600;
			} else {
				requiredSleep = requiredSleep + 540;
			}
		} else if (Age < 17) {

			if (fallAsleep >= 30) {
				requiredSleep = requiredSleep + 600;

			} else if (fallAsleep >= 20) {
				requiredSleep = requiredSleep + 540;
			} else {
				requiredSleep = requiredSleep + 480;
			}
		} else if (Age < 65) {

			if (fallAsleep >= 30) {
				requiredSleep = requiredSleep + 540;

			} else if (fallAsleep >= 20) {
				requiredSleep = requiredSleep + 480;

			} else {
				requiredSleep = requiredSleep + 420;
			}
		} else {

			if (fallAsleep >= 30) {
				requiredSleep = requiredSleep + 480;

			} else {
				requiredSleep = requiredSleep + 420;
			}
		}

		// Converting from total minutes to hours + minutes
		totalSleepTimeMinutes = fallAsleep + requiredSleep;

		long totalSleepHours = totalSleepTimeMinutes / 60;

		long totalSleepMinutes = totalSleepTimeMinutes % 60;

		night.setNHours(totalSleepHours);
		night.setNMinutes(totalSleepMinutes);

		localTime = localTime.minusHours(totalSleepHours);
		localTime = localTime.minusMinutes(totalSleepMinutes);

		// Formatting Bed time from military time to AM/PM
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		LocalTime bedTime = localTime;
		goToBed = dateTimeFormatter.format(bedTime);

		return goToBed;

	}

	// This was used in Testing Night Service methods
	public void setNightRepository(NightRepository nightRepository) {
		this.nightRepository = nightRepository;

	}

}
