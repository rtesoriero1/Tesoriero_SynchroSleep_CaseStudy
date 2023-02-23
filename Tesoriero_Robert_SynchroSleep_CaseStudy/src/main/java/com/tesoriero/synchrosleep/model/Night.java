package com.tesoriero.synchrosleep.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//This POJO is for the purpose of containing information like what time they would like to wake up in the morning
//The recommended time they should be in bed. And the amount of hours and minutes they would have spent sleeping if these recommendations are followed.


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table
public class Night {

/**
 * Variables to consider:
 * Average Time to Fall asleep
 * Amount of Rest Cycles needed
 * 
 */
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "Night ID")	
	Long nId; 
	
	
	@Column(name = "Wake up")	
	String nWake;
	
	@Column(name = "Bed Time")	
	String nBed;
	
	@Column(name = "Hours")	
	Long nHours;
	
	@Column(name = "Minutes")	
	Long nMinutes;
	
	

	
	
	
	
	@ManyToOne
	private Profile profile;
	
	@ManyToOne
	private Dream dream;

	
	
}
