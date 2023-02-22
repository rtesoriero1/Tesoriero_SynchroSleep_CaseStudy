package com.memory.demo.model;

//This POJO is used to handle a profile's Dream Journal. The dreams are tracked on a One to One relationship with Nights and a Many to One with Profiles
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Dream {

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Dream Id")
	Long dId;
	
	@Column(name = "Date")	
	String dreamDate; 

	@Column(name = "Dream Desc.")	
	String dreamJournal; 
	
	@OneToOne(targetEntity = Night.class)
	private Night night;
	
	
	@ManyToOne(targetEntity = Profile.class)
	private Profile profile;
}
