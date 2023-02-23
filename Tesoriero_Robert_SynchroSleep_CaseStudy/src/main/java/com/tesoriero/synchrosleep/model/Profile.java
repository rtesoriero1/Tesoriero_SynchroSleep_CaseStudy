package com.tesoriero.synchrosleep.model;

//This class handles profile information and has a Many to One relationship with Users, and contains the attributes needed to make Bed Time calculations.

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
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
@Table(name="profiles")
public class Profile {

	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "Profile ID")	
	Long pId; 	


	@Column(name = "Name")
	@Nonnull
	String pName;


	@Column(name = "Age")
	@Nonnull
	int pAge;


	@Column(name = "Sex")
	@Nonnull
	String pSex;


	@Column(name = "Weight(lb)")
	@Nonnull
	int pWeight;


	@Column(name = "Activity Level")
	@Nonnull
	String pActivity; 

	
	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;


	
	
	
	
}
