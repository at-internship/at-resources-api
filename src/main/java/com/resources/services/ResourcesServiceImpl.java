package com.resources.services;

import java.util.ArrayList;
import java.util.List;

import com.resources.configuration.OrikaConfiguration;
import com.resources.model.StoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resources.domain.CreateStoryResponse;
import com.resources.domain.Story;
import com.resources.dto.StoryDTO;
import com.resources.repository.StoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {

    private StoryRepository storyRepository;
    private OrikaConfiguration orikaConfiguration;

    @Autowired
    public ResourcesServiceImpl(StoryRepository storyRepository, OrikaConfiguration orikaConfiguration) {
        this.storyRepository = storyRepository;
        this.orikaConfiguration = orikaConfiguration;
    }
    @Autowired
    private StoryRepository storyRepository;

    @Override
    public CreateStoryResponse createStory(StoryDTO storyDTO) {
        Story story = new Story();

        story.setPriority(storyDTO.getPriority().getValue());
        CreateStoryResponse response = new CreateStoryResponse();

        story.setSprintId(new ObjectId());
        story.setUserId(new ObjectId());
        story.setPriority(storyDTO.getPriority().getValue());
        story.setName(storyDTO.getName());
        story.setDescription(storyDTO.getDescription());
        story.setAcceptanceCriteria(storyDTO.getAcceptanceCriteria());
        story.setStoryPoints(storyDTO.getStoryPoints());
        story.setProgress(storyDTO.getProgress());
        story.setStartDate(storyDTO.getStartDate());
        story.setDueDate(storyDTO.getDueDate());
        story.setCreateDate(storyDTO.getCreateDate());
        story.setStatus(storyDTO.getStatus());

        response.setId(storyRepository.save(story).getId().toString());
        log.info("Story saved with id: {}", response.getId());
        return response;
    }

    @Override
    public List<StoryDTO> getStories() {

        List<Story> storiesDB = storyRepository.findAll();
        List<StoryDTO> response = new ArrayList<>();
        for(Story storyDB : storiesDB ){
            StoryDTO aux = new StoryDTO();

            aux.setId(storyDB.getId().toString());
            aux.setSprintId(storyDB.getSprintId() != null ? storyDB.getSprintId().toString() : "");
            aux.setUserId(storyDB.getUserId() != null ? storyDB.getUserId().toString() : "");
            aux.setPriority(StoryDTO.Priority.valueOf(storyDB.getPriority()));
            aux.setName(storyDB.getName());
            aux.setDescription(storyDB.getDescription());
            aux.setAcceptanceCriteria(storyDB.getAcceptanceCriteria());
            aux.setStoryPoints(storyDB.getStoryPoints());
            aux.setProgress(storyDB.getProgress());
            aux.setStartDate(storyDB.getStartDate());
            aux.setDueDate(storyDB.getDueDate());
            aux.setCreateDate(storyDB.getCreateDate());
            aux.setStatus(storyDB.getStatus());
            aux.setPriority(StoryDTO.Priority.valueOf(storyDB.getPriority()));
            response.add(aux);

          }
          log.info("Consulted sucessfully on mongoDB");
          return response;
    }
    @Override
    public CreateStoryResponse createStory(StoryDTO storyDto) {
        CreateStoryResponse response = new CreateStoryResponse();
        storyDto.setSprint_id("");
        storyDto.setUser_id("");
        Story story = orikaConfiguration.map(storyDto, Story.class);
        response.setId(storyRepository.save(story).get_id().toString());
        log.info("Story saved with id: {}", response.getId());
        return response;
    }

}
