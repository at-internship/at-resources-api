package com.resources.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "stories")
@Data
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String sprint_id;
	private String user_id;
	private Integer priority;
	private String name;
	private String description;
	private String aceptance_criteria;
	private Integer story_points;
	private Integer progress;
	private LocalDate start_date;
	private LocalDate due_date;
	private LocalDate create_date;
	private Integer status;
	
	}