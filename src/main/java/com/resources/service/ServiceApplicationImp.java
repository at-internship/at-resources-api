package com.resources.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resources.model.Story;
import com.resources.repository.RepositoryApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceApplicationImp implements ServiceApplication {
	@Autowired
	private RepositoryApplication repositoryApplication;
	
	@Override
	public List<Story> getStories() {
		
		List<Story> response=repositoryApplication.findAll();
		log.info("Consulted sucessfully on mongoDB");
		return response;
	}
}