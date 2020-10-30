package com.resources.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class json {

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

    public json createDummy() {
        json Returning = new json();
        Returning.id = "r324";
        Returning.sprint_id = "ji34";
        Returning.user_id = "klewl76";
        Returning.priority = "14";
        Returning.name = "example";
        Returning.description = "example";
        Returning.acceptance_criteria = "example";
        Returning.story_points = "3";
        Returning.progress = "10";
        Returning.start_date = "2007/08/20";
        Returning.due_date = "2008/08/20";
        Returning.create_date = "2009/08/20";
        Returning.status = "1";
        return Returning;
    }

    public void setStart_date(String myDate) {
        this.start_date = myDate;
    }
    public void setDue_date(String myDate) {
        this.due_date = myDate;
    }
    public void setCreate_date(String myDate) {
        this.create_date = myDate;
    }

}
