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
public class Story{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private ObjectId id;
	private ObjectId sprintId;
    private ObjectId userId;
    private Integer priority;
    private String name;
    private String description;
    private String acceptanceCriteria;
    private Integer storyPoints;
    private Integer progress;
    private Date startDate;
    private Date dueDate;
    private Date createDate;
    private Integer status;
}