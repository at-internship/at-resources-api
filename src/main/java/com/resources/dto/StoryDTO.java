package com.resources.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

@Data
public class StoryDTO{

	private ObjectId id;
	private ObjectId sprint_id;
    private ObjectId user_id;
    private Priority priority;
    private String name;
    private String description;
    private String aceptance_criteria;
    private Integer story_points;
    private Integer progress;
    private Date start_date;
    private Date due_date;
    private Date create_date;
    private Integer status;
    
    public enum Priority {
        Low(3), Medium(2), High(1);
        @Getter
        private int value;
        private static Map<Integer, Priority> map = new HashMap<>();

        Priority(int value){
            this.value = value;
            System.out.println("Name coming from enum + " + name());
        }

        static {
            for (Priority priority : Priority.values()) map.put(priority.value, priority);
        }

        public static Priority valueOf(int value){
        	return map.get(value);
		}
    }
}
