package com.resources.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.resources.configuration.OrikaConfiguration;
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

import static com.resources.errorhandling.Validations.validationPostPut;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {

	private final StoryRepository storyRepository;
	private final OrikaConfiguration mapper;

	public ResourcesServiceImpl(StoryRepository storyRepository, OrikaConfiguration orikaConfiguration) {
		this.storyRepository = storyRepository;
		this.mapper = orikaConfiguration;
	}

	@Override
	public CreateStoryResponse createStory(StoryRequestDTO storyRequestDTO) {
		storyRequestDTO.setSprintId("");
		storyRequestDTO.setUserId("");
		validationPostPut(storyRequestDTO);
		Story story = mapper.map(storyRequestDTO, Story.class);
		CreateStoryResponse response = new CreateStoryResponse();
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
		log.info("Consulted successfully on mongoDB");
		return response;
	}//END getStories
  
	@Override
	public StoryRequestDTO updateStory(StoryRequestDTO storyRequestDTO, String id) {
		storyRequestDTO.setId(id);
		Story story;
		validationPostPut(storyRequestDTO);
		if (storyRepository.existsById(id)) {
			Optional<Story> existStory = storyRepository.findById(id);
			story = mapper.map(storyRequestDTO, Story.class);
			story.setSprint_id(existStory.get().getSprint_id());
			story.setUser_id(existStory.get().getUser_id());
			storyRepository.save(story);
			storyRequestDTO.setSprintId(story.getSprint_id().toString());
			storyRequestDTO.setUserId(story.getUser_id().toString());
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