package com.memory.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tesoriero.synchrosleep.model.Dream;
import com.tesoriero.synchrosleep.repository.DreamRepository;
import com.tesoriero.synchrosleep.service.DreamService;

@ExtendWith(MockitoExtension.class)
public class DreamServiceTest {

	@Mock
	DreamRepository dreamRepository;
	
	@ParameterizedTest
	@ValueSource(longs = {7,3,9})
public void getDreamById(Long bId) {
	
		Dream testDream = new Dream();
		testDream.setDId(bId);
		
		when(dreamRepository.findById(bId)).thenReturn(Optional.of(testDream));
		
		DreamService dreamService = new DreamService();
		dreamService.setDreamRepository(dreamRepository);
		
		//When
		Optional<Dream> actualDream = dreamService.getDreamById(bId);
		Dream actualDream_ = actualDream.get();
		
		//Then
		assertEquals(testDream.getDId(),actualDream_.getDId());
		
		
}
	
}
