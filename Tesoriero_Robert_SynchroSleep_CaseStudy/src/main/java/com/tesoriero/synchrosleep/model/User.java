package com.tesoriero.synchrosleep.model;

//This POJO contains information needed for the User to register an account. It has a One to many relationship with Profiles. 



import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "User ID")
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	
	@Transient
	boolean loggedIn;

	
	
	
	
}
