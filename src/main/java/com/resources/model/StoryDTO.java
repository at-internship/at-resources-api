package com.resources.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StoryDTO {

    private String _id;
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