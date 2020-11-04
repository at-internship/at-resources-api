package com.resources.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "stories") 
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private ObjectId id;
	private ObjectId sprint_id;
    private ObjectId user_id;
    private Integer priority;
    private String name;
    private String description;
    private String aceptance_criteria;
    private Integer story_points;
    private Integer progress;
    private Date start_date;
    private Date due_date;
    private Date create_date;
    private Integer status;
}
