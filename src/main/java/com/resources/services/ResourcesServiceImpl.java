package com.resources.services;

import java.util.ArrayList;
import java.util.List;

import com.resources.configuration.OrikaConfiguration;
import com.resources.model.StoryDTO;
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
    private OrikaConfiguration orikaConfiguration;

    @Autowired
    public ResourcesServiceImpl(StoryRepository storyRepository, OrikaConfiguration orikaConfiguration) {
        this.storyRepository = storyRepository;
        this.orikaConfiguration = orikaConfiguration;
    }

    public CreateStoryResponse createStory(StoryDTO storyDTO) {
        CreateStoryResponse response = new CreateStoryResponse();
        storyDTO.setSprint_id("");
        storyDTO.setUser_id("");
        Story story = orikaConfiguration.map(storyDTO, Story.class);
        response.setId(storyRepository.save(story).getId().toString());
        log.info("Story saved with id: {}", response.getId());
        return response;
    }

    @Override
    public List<Story> getStories() {

        List<com.resources.model.Story> storiesDB = storyRepository.findAll();
        List<Story> response = new ArrayList<>();
        for(com.resources.model.Story storyDB : storiesDB ){
            Story aux = new Story();
            aux.setAcceptance_criteria(storyDB.getAcceptance_criteria());
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
            aux.setPriority(storyDB.getPriority());
            response.add(aux);
        }

        System.out.println(Story.Priority.valueOf(1));
        log.info("Consulted sucessfully on mongoDB");
        return response;
    }
}
