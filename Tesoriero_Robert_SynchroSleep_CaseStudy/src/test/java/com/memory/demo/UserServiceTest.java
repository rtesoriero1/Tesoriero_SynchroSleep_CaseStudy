package com.memory.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tesoriero.synchrosleep.model.User;
import com.tesoriero.synchrosleep.repository.UserRepository;
import com.tesoriero.synchrosleep.service.UserService;

public class UserServiceTest {


UserService userService; 

@Mock
UserRepository userRepository;


@BeforeEach
public void prep() {
	MockitoAnnotations.openMocks(this);
	
	userService = new UserService ();
	userService.setUserRepository(userRepository);
}


@Test
public void testGetAllUserService() {
	User user = new User();
	User user2 = new User();
	User user3 = new User();
	
	user.setEmail("Test1@gmail.com");
	user2.setEmail("Test2@gmail.com");
	user3.setEmail("Test3@gmail.com");
	
	//Adding Users to a List for testing
	List<User> testUsers = new ArrayList<>();
	testUsers.add(user3);
	testUsers.add(user2);
	testUsers.add(user);
	
	when(userService.getAllUsers()).thenReturn(testUsers);
	
	//When
	List<User> actualUsers = userService.getAllUsers();
	
	//Then
	assertThat(actualUsers).isNotNull();
    assertThat(actualUsers.size()).isEqualTo(testUsers.size());
    assertThat(actualUsers).containsAll(testUsers);
}

}
