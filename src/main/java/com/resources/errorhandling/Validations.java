package com.resources.errorhandling;

import com.resources.domain.StoryRequestDTO;
import com.resources.exception.BadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.resources.errorhandling.PathErrorMessage.PATH_POST_PUT;

@Slf4j
@Component
public class Validations {

    public static void validationPostPut(StoryRequestDTO requestDTO) {

        if (isNullOrEmpty(requestDTO.getPriority()))
            throw new BadRequest("The priority field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (!requestDTO.getPriority().equals("High") && !requestDTO.getPriority().equals("Medium") && !requestDTO.getPriority().equals("Low"))
            throw new BadRequest("The priority field only accepts 3 values {High, Medium, Low}", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (isNullOrEmpty(requestDTO.getName()))
            throw new BadRequest("The name field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (isNullOrEmpty(requestDTO.getDescription()))
            throw new BadRequest("The description field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (isNullOrEmpty(requestDTO.getAcceptanceCriteria()))
            throw new BadRequest("The acceptanceCriteria field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (isNull(requestDTO.getStoryPoints()))
            throw new BadRequest("The storyPoints field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        else if (requestDTO.getStoryPoints() == 4 || requestDTO.getStoryPoints() == 6 || requestDTO.getStoryPoints() == 7 || requestDTO.getStoryPoints() > 8 || requestDTO.getStoryPoints() < 1)
            throw new BadRequest("The storyPoints field only accepts the following values {1, 2, 3, 5, 8}", PATH_POST_PUT, HttpStatus.BAD_REQUEST);

        if (isNull(requestDTO.getProgress()))
            requestDTO.setProgress(0);
        else if (requestDTO.getProgress() > 100 || requestDTO.getProgress() < 0)
            throw new BadRequest("The progress field they only receive values from 0 to 100", PATH_POST_PUT, HttpStatus.BAD_REQUEST);

        if (isNull(requestDTO.getStatus()))
            throw new BadRequest("The status field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (requestDTO.getStatus() != 0 && requestDTO.getStatus() != 1)
            throw new BadRequest("The status field only accepts values 0 or 1", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (isNullOrEmpty(requestDTO.getStartDate()))
            throw new BadRequest("The startDate field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (isNullOrEmpty(requestDTO.getDueDate()))
            throw new BadRequest("The dueDate field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        if (isNullOrEmpty(requestDTO.getCreateDate()))
            throw new BadRequest("The createDate field value should not be null or empty", PATH_POST_PUT, HttpStatus.BAD_REQUEST);

        try {
            isValidate(requestDTO.getStartDate());

        } catch (ParseException e) {
            throw new BadRequest("The correct date format for the StartDate field must be YYYY-MM-DD", PATH_POST_PUT , HttpStatus.BAD_REQUEST);
        }
        try {
            isValidate(requestDTO.getDueDate());
        } catch (ParseException e) {
            throw new BadRequest("The correct date format for the DueDate field must be YYYY-MM-DD", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        }
        try {
            isValidate(requestDTO.getCreateDate());
        } catch (ParseException e) {
            throw new BadRequest("The correct date format for the CreateDate field must be YYYY-MM-DD", PATH_POST_PUT, HttpStatus.BAD_REQUEST);
        }
    }
    private static boolean isNullOrEmpty(String stringValidate){
        if(stringValidate == null || stringValidate.isEmpty())
            return true;
        return false;
    }
    private static void isValidate(String dateValidate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
        dateFormat.setLenient(false);
        dateFormat.parse(dateValidate);
    }
    private static boolean isNull(Integer intValidate){
        if(intValidate==null)
            return true;
        return false;
    }
}
