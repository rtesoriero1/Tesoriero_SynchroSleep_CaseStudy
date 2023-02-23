package com.memory.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tesoriero.synchrosleep.model.User;
import com.tesoriero.synchrosleep.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

	
	@Mock
	UserRepository userRepository;
	
	
	@Test
	public void testUserFindByEmail() {
		User user = new User();
		String testEmail = "EmailTest@gmail.com"; 
		user.setEmail(testEmail);
		
		when(userRepository.findByEmail(testEmail)).thenReturn(user);
		
		//when
		//Optional incase it comes back null
		Optional<User> result = Optional.of(userRepository.findByEmail(testEmail));
		
		//then 
		assertEquals(result.get().getEmail(), testEmail);
		
	}
}
