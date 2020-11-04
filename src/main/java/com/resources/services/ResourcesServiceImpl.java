package com.resources.services;

import com.resources.configuration.OrikaConfiguration;
import com.resources.model.StoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
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
