package com.resources.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class StoryDTO{

	private String id;
	private String sprint_id;
    private String user_id;
//    private Priority priority;
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
    
//    public enum Priority {
//        Low(3), Medium(2), High(1);
//        @Getter
//        private int value;
//        private static Map<Integer, Priority> map = new HashMap<>();
//
//        Priority(int value){
//            this.value = value;
//        }
//
//        static {
//            for (Priority priority : Priority.values()) map.put(priority.value, priority);
//        }
//
//        public static Priority valueOf(int value){
//        	return map.get(value);
//		}
//    }
}
