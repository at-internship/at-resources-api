package com.resources.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "stories")
@Getter
@Setter
@ToString
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String sprint_id;
	private String user_id;
	private String priority;
	private String name;
	private String description;
	private String acceptance_criteria;
	private String story_points;
	private String progress;
	private String start_date;
	private String due_date;
	private String create_date;
	private String status;
}
