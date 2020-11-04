package com.resources.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "stories") 
@Data
public class Story {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String _id;
}
