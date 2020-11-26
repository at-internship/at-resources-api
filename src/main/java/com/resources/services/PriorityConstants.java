package com.resources.services;

import java.util.ArrayList;
import java.util.List;

public enum PriorityConstants {
    High(1),
    Medium(2),
    Low(3);

    private int ord;

    PriorityConstants(int ord) {
        this.ord = ord;
    }
    public int getOrd() {
        return this.ord;
    }

    public static String getPriority(Integer ord){
        for(PriorityConstants c: PriorityConstants.values()){
            if(c.getOrd()==ord){
                return c.name();
            }
        }
        return("UNKNOWN");
    }

    public static List<String> getPriorityNames(){

        List<String> names = new ArrayList<>();
        for (PriorityConstants c: PriorityConstants.values()) {
            names.add(c.name());
        }
        return names;
    }
}