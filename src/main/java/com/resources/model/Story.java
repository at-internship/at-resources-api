package com.resources.model;

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
	private String _id; 
	//@GeneratedValue
	private ObjectId sprint_id;
	//@GeneratedValue
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
