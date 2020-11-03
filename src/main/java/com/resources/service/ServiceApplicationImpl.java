package com.resources.service;

import java.util.List;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.resources.domain.CreateStoryRequest;
import com.resources.errorhandling.HttpExceptionMessage;
import com.resources.errorhandling.TypeError;
import com.resources.model.Story;
import com.resources.repository.RepositoryApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class ServiceApplicationImpl implements ServiceApplication {
    @Autowired
    private RepositoryApplication repositoryApplication;

    @Override
    public List<Story> getStories() {

        List<Story> response = repositoryApplication.findAll();
        log.info("Consulted sucessfully on mongoDB");
        return response;
    }


    @Override
    public Story updateStory(CreateStoryRequest request, String id) throws NotFoundException {
        Story story = new Story();
        if (repositoryApplication.existsById(id)) {
            story.setId(request.getId());
            story.setAceptance_criteria(request.getAceptance_criteria());
            story.setSprint_id(request.getSprint_id());
            story.setUser_id(request.getUser_id());
            story.setPriority(request.getPriority());
            story.setName(request.getName());
            story.setDescription(request.getDescription());
            story.setAceptance_criteria(request.getAceptance_criteria());
            story.setStory_points(request.getStory_points());
            story.setProgress(request.getProgress());
            story.setStart_date(request.getStart_date());
            story.setDue_date(request.getDue_date());
            story.setCreate_date(request.getCreate_date());
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


//    @Override
//    public Story findById(String id) {
//        Story story = new Story();
//        if (!repositoryApplication.existsById(id)) {
////		TypeError error = new TypeError(HttpStatus.NOT_FOUND, HttpExceptionMessage.IdNotFound404, "/api/v1/story" + id);
//            throw new TypeError(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), HttpExceptionMessage.IdNotFound404, "/api/v1/story" + id);
//        } else if (repositoryApplication.findById(id).isPresent()) {
//            story = repositoryApplication.findById(id).get();
//        }
//        return story;
//    }

}