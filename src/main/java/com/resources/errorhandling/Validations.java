package com.resources.errorhandling;

import com.resources.domain.StoryRequestDTO;
import com.resources.exception.BadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Slf4j
@Component
public class Validations {

    public static void validationPostPut(StoryRequestDTO requestDTO) {

        if (!requestDTO.getPriority().equals("High") && !requestDTO.getPriority().equals("Medium") && !requestDTO.getPriority().equals("Low"))
            throw new BadRequest("The priority field only accepts 3 values {High, Medium, Low}", "/story/", HttpStatus.BAD_REQUEST);
        if (requestDTO.getName() == null || requestDTO.getName().isEmpty())
            throw new BadRequest("The name field value should not be null or empty", "/story/", HttpStatus.BAD_REQUEST);
        if (requestDTO.getDescription() == null || requestDTO.getDescription().isEmpty())
            throw new BadRequest("The description field value should not be null or empty", "/story/", HttpStatus.BAD_REQUEST);
        if (requestDTO.getAcceptanceCriteria() == null || requestDTO.getAcceptanceCriteria().isEmpty())
            throw new BadRequest("The acceptanceCriteria field value should not be null or empty", "/story/", HttpStatus.BAD_REQUEST);
        if (requestDTO.getStoryPoints() == 4 || requestDTO.getStoryPoints() == 6 || requestDTO.getStoryPoints() == 7 || requestDTO.getStoryPoints() > 8 || requestDTO.getStoryPoints() < 1)
            throw new BadRequest("The storyPoints field only accepts the following values {1, 2, 3, 5, 8}", "/story/", HttpStatus.BAD_REQUEST);
        if (requestDTO.getProgress() == null)
            requestDTO.setProgress(0);
        if (requestDTO.getProgress() > 100 || requestDTO.getProgress() < 0)
            throw new BadRequest("The progress field they only receive values from 0 to 100", "/story/", HttpStatus.BAD_REQUEST);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            dateFormat.setLenient(false);
            dateFormat.parse(requestDTO.getStartDate());
        } catch (ParseException e) {
            throw new BadRequest("The correct date format for the StartDate field must be YYYY-MM-DD", "/story/", HttpStatus.BAD_REQUEST);
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            dateFormat.setLenient(false);
            dateFormat.parse(requestDTO.getDueDate());
        } catch (ParseException e) {
            throw new BadRequest("The correct date format for the DueDate field must be YYYY-MM-DD", "/story/", HttpStatus.BAD_REQUEST);
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            dateFormat.setLenient(false);
            dateFormat.parse(requestDTO.getCreateDate());
        } catch (ParseException e) {
            throw new BadRequest("The correct date format for the CreateDate field must be YYYY-MM-DD", "/story/", HttpStatus.BAD_REQUEST);
        }
    }
}
