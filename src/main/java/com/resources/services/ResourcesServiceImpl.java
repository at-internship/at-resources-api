package com.resources.services;

import java.util.ArrayList;
import java.util.List;

import com.resources.configuration.OrikaMapper;
import com.resources.dto.StoryDTO;
import com.resources.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resources.domain.CreateStoryResponse;
import com.resources.repository.StoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {

    private StoryRepository storyRepository;
    private OrikaMapper mapper;

    @Autowired
    public ResourcesServiceImpl(StoryRepository storyRepository, OrikaMapper orikaMapper) {
        this.storyRepository = storyRepository;
        this.mapper = orikaMapper;
    }

    @Override
    public CreateStoryResponse createStory(StoryDTO storyDTO) {
        CreateStoryResponse response = new CreateStoryResponse();
        storyDTO.setSprintId("");
        storyDTO.setUserId("");
        Story story = mapper.map(storyDTO, Story.class);
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

//            aux.setId(storyDB.get_id().toString());
//            aux.setSprintId(storyDB.getSprintId() != null ? storyDB.getSprintId().toString() : "");
//            aux.setUserId(storyDB.getUserId() != null ? storyDB.getUserId().toString() : "");
//            aux.setPriority(StoryDTO.Priority.valueOf(storyDB.getPriority()));
//            aux.setName(storyDB.getName());
//            aux.setDescription(storyDB.getDescription());
//            aux.setAcceptanceCriteria(storyDB.getAcceptanceCriteria());
//            aux.setStoryPoints(storyDB.getStoryPoints());
//            aux.setProgress(storyDB.getProgress());
//            aux.setStartDate(storyDB.getStartDate());
//            aux.setDueDate(storyDB.getDueDate());
//            aux.setCreateDate(storyDB.getCreateDate());
//            aux.setStatus(storyDB.getStatus());
//            aux.setPriority(StoryDTO.Priority.valueOf(storyDB.getPriority()));
            mapper.map(storyDB, StoryDTO.class);
            response.add(aux);

          }
          log.info("Consulted successfully on mongoDB");
          return response;
    }
}
