package com.resources.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class StoryDTO{

	private String id;
	private String sprintId;
    private String userId;
//    private Priority priority;
    private String priority;
    private String name;
    private String description;
    private String acceptanceCriteria;
    private String storyPoints;
    private String progress;
    private String startDate;
    private String dueDate;
    private String createDate;
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
