package com.resources.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
import com.resources.repository.StoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public CreateStoryResponse createStory(Story storyDTO) {
        com.resources.domain.Story story = new com.resources.domain.Story();

        story.setPriority(storyDTO.getPriority().getValue());
        CreateStoryResponse response = new CreateStoryResponse();
        story.setSprint_id(new ObjectId());
        story.setUser_id(new ObjectId());
		System.out.println(storyDTO.getPriority().getValue());
        //response.setId(storyRepository.save(story).get_id());
        log.info("Story saved with id: {}", response.getId());
        return response;
    }

    @Override
    public List<Story> getStories() {

        List<com.resources.domain.Story> storiesDB = storyRepository.findAll();
        List<Story> response = new ArrayList<>();
        for(com.resources.domain.Story storyDB : storiesDB ){
            Story aux = new Story();
            aux.setAcceptance_criteria(storyDB.getAceptance_criteria());
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
            aux.setPriority(Story.Priority.valueOf(storyDB.getPriority()));
            response.add(aux);
        }

        System.out.println(Story.Priority.valueOf(1));
        log.info("Consulted sucessfully on mongoDB");
        return response;
    }
}
