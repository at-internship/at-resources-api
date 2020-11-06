package com.resources.services;

import java.util.ArrayList;
import java.util.List;

//import com.resources.mapper.OrikaMapper;
import com.resources.configuration.OrikaConfiguration;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
import com.resources.domain.StoryRequestDTO;
import com.resources.errorhandling.HttpExceptionMessage;
import com.resources.errorhandling.PathErrorMessage;
import com.resources.exception.NotFoundException;
import com.resources.repository.StoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {

	private final StoryRepository storyRepository;
	//private final OrikaMapper mapper;
	private final OrikaConfiguration mapper;

	//public ResourcesServiceImpl(StoryRepository storyRepository, OrikaMapper orikaMapper) {
	public ResourcesServiceImpl(StoryRepository storyRepository, OrikaConfiguration orikaConfiguration) {
		this.storyRepository = storyRepository;
		//this.mapper = orikaMapper;
		this.mapper = orikaConfiguration;
	}

	@Override
	public CreateStoryResponse createStory(StoryRequestDTO storyRequestDTO) {
		Story story = mapper.map(storyRequestDTO, Story.class);
//		story.setPriority(storyDTO.getPriority().getValue());
		CreateStoryResponse response = new CreateStoryResponse();

		//story = mapper.map(storyRequestDTO, Story.class);
/*
		story.setSprintId(new ObjectId());
		story.setUserId(new ObjectId());
//		story.setPriority(storyDTO.getPriority().getValue());
		story.setName(storyRequestDTO.getName());
		story.setDescription(storyRequestDTO.getDescription());
		story.setAcceptanceCriteria(storyRequestDTO.getAcceptanceCriteria());
		story.setStoryPoints(storyRequestDTO.getStoryPoints());
		story.setProgress(storyRequestDTO.getProgress());
		story.setStartDate(storyRequestDTO.getStartDate());
		story.setDueDate(storyRequestDTO.getDueDate());
		story.setCreateDate(storyRequestDTO.getCreateDate());
		story.setStatus(storyRequestDTO.getStatus());
*/
		response.setId(storyRepository.save(story).getId().toString());
		log.info("Story saved with id: {}", response.getId());
		return response;
	}//END createStory
	
	@Override
	public List<StoryRequestDTO> getStories() {

		List<Story> storiesDB = storyRepository.findAll();
		List<StoryRequestDTO> response = new ArrayList<>();
		for (Story storyDB : storiesDB) {
			StoryRequestDTO aux = mapper.map(storyDB,StoryRequestDTO.class);
			response.add(aux);
		}
		/*
		List<StoryRequestDTO> response = new ArrayList<>();
		for (Story storyDB : storiesDB) {
			StoryRequestDTO aux = new StoryRequestDTO();

			aux.setId(storyDB.getId().toString());
			aux.setSprintId(storyDB.getSprintId() != null ? storyDB.getSprintId().toString() : "");
			aux.setUserId(storyDB.getUserId() != null ? storyDB.getUserId().toString() : "");
			aux.setPriority(StoryRequestDTO.Priority.valueOf(storyDB.getPriority()));
			aux.setName(storyDB.getName());
			aux.setDescription(storyDB.getDescription());
			aux.setAcceptanceCriteria(storyDB.getAcceptanceCriteria());
			aux.setStoryPoints(storyDB.getStoryPoints());
			aux.setProgress(storyDB.getProgress());
			aux.setStartDate(storyDB.getStartDate());
			aux.setDueDate(storyDB.getDueDate());
			aux.setCreateDate(storyDB.getCreateDate());
			aux.setStatus(storyDB.getStatus());
			aux.setPriority(StoryRequestDTO.Priority.valueOf(storyDB.getPriority()));
			response.add(aux);
		}*/
		log.info("Consulted successfully on mongoDB");
		return response;
	}//END getStories
  
  @Override
	public StoryRequestDTO updateStory(StoryRequestDTO storyRequestDTO, String id) {
		Story story = mapper.map(storyRequestDTO, Story.class);
		if (storyRepository.existsById(id)) {/*
			story.setId(new ObjectId(id));
			story.setSprintId(new ObjectId());
			story.setUserId(new ObjectId());
//			story.setPriority(storyDTO.getPriority().getValue());
			story.setName(storyRequestDTO.getName());
			story.setDescription(storyRequestDTO.getDescription());
			story.setAcceptanceCriteria(storyRequestDTO.getAcceptanceCriteria());
			story.setStoryPoints(storyRequestDTO.getStoryPoints());
			story.setProgress(storyRequestDTO.getProgress());
			story.setStartDate(storyRequestDTO.getStartDate());
			story.setDueDate(storyRequestDTO.getDueDate());
			story.setCreateDate(storyRequestDTO.getCreateDate());
			story.setStatus(storyRequestDTO.getStatus());*/
			storyRepository.save(story);
			storyRequestDTO.setId(story.getId().toString());
			storyRequestDTO.setSprintId(story.getSprintId().toString());
			storyRequestDTO.setUserId(story.getUserId().toString());
			return storyRequestDTO;
		} else {
      throw new NotFoundException(HttpExceptionMessage.IDNOTFOUND, PathErrorMessage.pathApiDelete,HttpStatus.NOT_FOUND);
		}
	}//END updateStory
	
	@Override
	public void deleteStory(String id) {
		if (storyRepository.existsById(id)) {
			storyRepository.deleteById(id);
		} else {
			throw new NotFoundException(HttpExceptionMessage.IDNOTFOUND, PathErrorMessage.pathApiDelete,HttpStatus.NOT_FOUND);
		}
	}//End deleteStory

}// End class