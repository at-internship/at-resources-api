package com.resources.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.resources.AtResourcesApi2Application;
import com.resources.domain.CreateStoryResponse;
import com.resources.domain.Story;
import com.resources.dto.StoryDTO;
import com.resources.errorhandling.HttpExceptionMessage;
import com.resources.errorhandling.PathErrorMessage;
import com.resources.exception.NotFoundException;
import com.resources.repository.StoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {

	@Autowired
	private StoryRepository storyRepository;
	
	@Override
    public CreateStoryResponse createStory(StoryDTO storyDTO) {
        Story story = new Story();

        story.setPriority(storyDTO.getPriority().getValue());
        CreateStoryResponse response = new CreateStoryResponse();
        story.setSprint_id(new ObjectId());
        story.setUser_id(new ObjectId());
		System.out.println(storyDTO.getPriority().getValue());
        log.info("Story saved with id: {}", response.getId());
        return response;
    }
	
	@Override
    public List<StoryDTO> getStories() {

        List<Story> storiesDB = storyRepository.findAll();
        List<StoryDTO> response = new ArrayList<>();
        for(Story storyDB : storiesDB ){
            StoryDTO aux = new StoryDTO();
            aux.setAceptance_criteria(storyDB.getAceptance_criteria());
            aux.setDescription(storyDB.getDescription());
            aux.setCreate_date(storyDB.getCreate_date());
            aux.setDue_date(storyDB.getDue_date());
            aux.setName(storyDB.getName());
            aux.setProgress(storyDB.getProgress());
            aux.setSprint_id(storyDB.getSprint_id());
            aux.setStart_date(storyDB.getStart_date());
            aux.setStatus(storyDB.getStatus());
            aux.setStory_points(storyDB.getStory_points());
            aux.setUser_id(storyDB.getUser_id());
            aux.setPriority(StoryDTO.Priority.valueOf(storyDB.getPriority()));
            response.add(aux);
        }

        System.out.println(StoryDTO.Priority.valueOf(1));
        log.info("Consulted sucessfully on mongoDB");
        return response;
    }
	
	@Override
	public void deleteStory(String id) {
		if (storyRepository.existsById(id)) {
			AtResourcesApi2Application.logger.info("Deleting story...");
			storyRepository.deleteById(id);
		} else {
			AtResourcesApi2Application.logger.error("Error. Not Found.");
			throw new NotFoundException(HttpExceptionMessage.IDNOTFOUND, PathErrorMessage.pathApiDelete,HttpStatus.NOT_FOUND);
		}
	}

}// End class
