package com.resources.domain;

import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CreateStoryRequest{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private ObjectId sprintId;
	private ObjectId userId;
	private Integer priority;
	private String name;
	private String description;
	private String aceptanceCriteria;
	private Integer storyPoints;
	private Integer progress;
	private Date startDate;
	private Date dueDate;
	private Date createDate;
	private Integer status;
	
	
	public CreateStoryRequest(ObjectId sprintId, ObjectId userId, Integer priority, String name,
			String description, String aceptanceCriteria, Integer storyPoints, Integer progress, Date startDate,
			Date dueDate, Date createDate, Integer status) {
	
		this.sprintId = sprintId;
		this.userId = userId;
		this.priority = priority;
		this.name = name;
		this.description = description;
		this.aceptanceCriteria = aceptanceCriteria;
		this.storyPoints = storyPoints;
		this.progress = progress;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.createDate = createDate;
		this.status = status;
	}
}