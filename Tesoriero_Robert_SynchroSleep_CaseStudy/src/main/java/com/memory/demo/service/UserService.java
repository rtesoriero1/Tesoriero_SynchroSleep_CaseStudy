package com.memory.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.demo.model.User;
import com.memory.demo.repository.UserRepository;


//Used to implement User Services 
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	//Creating an ArrayList to populate with users from database 
	 public List<User> getAllUsers() {
	       List<User> users = new ArrayList<User>();
	       userRepository.findAll().forEach(users::add);
	       return users;
	   }
	 
//Used to find Users specifically by their Id. 
	public Optional<User> getUserById(Long uId) {
		return userRepository.findById(uId);
	}

	
	//JpaRepository method used to add a User to the database 
	public void addUser(User user) {
		userRepository.save(user);
		
	}

	//Used to update the information of a user by Id 
	public void updateUser(Long uId, User user) {
		 Optional<User> userData = userRepository.findById(uId);

	       if (userData.isPresent()) {
	          User targetUser = userData.get();
	          targetUser.setEmail(user.getEmail());
	          targetUser.setPassword(user.getPassword());
	           addUser(targetUser);
	       }
		
	}

	//Used to delete a user by Id 
	public void deleteUser(Long uId) {
		userRepository.deleteById(uId);
		
	}

	///Custom query to find a User by Email. 
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	//Used for testing purposes. 
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}
	
	
	
}
