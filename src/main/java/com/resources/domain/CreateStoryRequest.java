package com.resources.domain;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateStoryRequest{
	
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
	
	
	public CreateStoryRequest(String sprint_id, String user_id, Integer priority, String name,
			String description, String aceptance_criteria, Integer story_points, Integer progress, LocalDate start_date,
			LocalDate due_date, LocalDate create_date, Integer status) {
	
		this.sprint_id = sprint_id;
		this.user_id = user_id;
		this.priority = priority;
		this.name = name;
		this.description = description;
		this.aceptance_criteria = aceptance_criteria;
		this.story_points = story_points;
		this.progress = progress;
		this.start_date = start_date;
		this.due_date = due_date;
		this.create_date = create_date;
		this.status = status;
	}
}