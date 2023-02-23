package com.tesoriero.synchrosleep.model;


//This a POJO that handles the Customer's bank information, it is being linked to the User class 
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class BankInfo {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "Customer ID")	
	private Long cId; 	


	
	@Column(name = "Credit Card")
	private String cardNum;
	
	@Column(name = "Security Code")
	private String secCode;
	
	
	@OneToOne(targetEntity = User.class)
	private User user; 
	
	}
	

	

