package com.tesoriero.synchrosleep.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesoriero.synchrosleep.model.Dream;
import com.tesoriero.synchrosleep.repository.DreamRepository;
//Used for Dream services 
@Service
public class DreamService {

	@Autowired
	DreamRepository dreamRepository;
	
	
	//Add new instances of Dream object to the database. 
	public void addNight(Dream dream) {
		dreamRepository.save(dream);

	}

	//Used to get a Specific dream by Id
	public Optional<Dream> getDreamById(Long dId) {
		return dreamRepository.findById(dId);
	}
	
	//Used to get all dreams so that they can be listed in a table on the Dream Journal html page 
	public List<Dream> getAllDreams() {
		   List<Dream> dreams = new ArrayList<Dream>();
		   dreamRepository.findAll().forEach(dreams::add);
	       return dreams;
	}

	//Used for testing purposes 
	public void setDreamRepository(DreamRepository dreamRepository) {
		this.dreamRepository = dreamRepository;
		
	}
}
