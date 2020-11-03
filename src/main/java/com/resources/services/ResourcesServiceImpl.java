package com.resources.services;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.resources.domain.CreateStoryRequest;
import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
import com.resources.repository.RepositoryApplication;
import com.resources.repository.StoryRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {
	
	@Autowired
	private RepositoryApplication repositoryApplication;
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Override
    public List<Story> getStories() {

        List<Story> response = repositoryApplication.findAll();
        log.info("Consulted sucessfully on mongoDB");
        return response;
    }

	@Override
	public CreateStoryResponse createStory(Story story) {
		CreateStoryResponse response = new CreateStoryResponse();
		story.setSprintId(new ObjectId());
		story.setUserId(new ObjectId());
		response.setId(storyRepository.save(story).getId());
		log.info("Story saved with id: {}", response.getId());
		return response;
	}
	
	@Override
	public Story updateStory(CreateStoryRequest request, String id) throws NotFoundException {
		Story story = new Story();
        if (repositoryApplication.existsById(id)) {
            story.setId(request.getId());
            story.setAceptanceCriteria(request.getAceptanceCriteria());
            story.setSprintId(request.getSprintId());
            story.setUserId(request.getUserId());
            story.setPriority(request.getPriority());
            story.setName(request.getName());
            story.setDescription(request.getDescription());
            story.setAceptanceCriteria(request.getAceptanceCriteria());
            story.setStoryPoints(request.getStoryPoints());
            story.setProgress(request.getProgress());
            story.setStartDate(request.getStartDate());
            story.setDueDate(request.getDueDate());
            story.setCreateDate(request.getCreateDate());
            story.setStatus(request.getStatus());

            log.info("Create sucessfully on mongoDB" + request);
            log.info("Create sucessfully on mongoDB" + story);

            story.setId(id);
            return repositoryApplication.save(story);
        } else {
//            throw new TypeError(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), HttpExceptionMessage.IdNotFound404, "/api/v1/story" + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found", new Exception());
        }
	}
}
