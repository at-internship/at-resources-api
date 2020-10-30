package com.resources.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Sprint {

    private String id;
    private String sprint_id;
    private String user_id;
    private int priority;
    private String name;
    private String description;
    private String acceptance_criteria;
    private int story_points;
    private int progress;
    private Date start_date;
    private Date due_date;
    private Date create_date;
    private int status;

    public Date getStart_date() {
        return this.start_date;
    }
    public Date getDue_date() {
        return this.due_date;
    }
    public Date getCreate_date() {
        return this.create_date;
    }




}
