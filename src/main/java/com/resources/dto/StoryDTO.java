package com.resources.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class StoryDTO{

	private String id;
	private String sprintId;
    private String userId;
    private Priority priority;
    private String name;
    private String description;
    private String acceptanceCriteria;
    private Integer storyPoints;
    private Integer progress;
    private Date startDate;
    private Date dueDate;
    private Date createDate;
    private Integer status;
    
    public enum Priority {
        Low(3), Medium(2), High(1);
        @Getter
        private int value;
        private static Map<Integer, Priority> map = new HashMap<>();

        Priority(int value){
            this.value = value;
        }

        static {
            for (Priority priority : Priority.values()) map.put(priority.value, priority);
        }

        public static Priority valueOf(int value){
        	return map.get(value);
		}
    }
}
