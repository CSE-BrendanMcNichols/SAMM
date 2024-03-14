package backEnd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This is an elective class
 * 
 * @author Sree
 */

public class Elective {
    private ArrayList<Course> courses;
    private String electiveName;
    private int hours;
    private UUID uuid;
    //TODO - implement the rest of the methods from the UML diagram

    public Elective(ArrayList<Course> courses, String electiveName, int hours, UUID uuid){
        this.courses = courses;
        this.electiveName = electiveName;
        this.hours = hours;
        this.uuid = uuid;
    }

    public Elective(){
        this.courses = new ArrayList<Course>();
        this.electiveName = "default name";
        this.hours = 0;
        this.uuid = UUID.randomUUID();
    }

    public void displayName(){
        System.out.println(electiveName);
    }

    public UUID getUuid(){
        return this.uuid;
    }
}
