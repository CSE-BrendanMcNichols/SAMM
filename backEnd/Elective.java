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

    Elective(ArrayList<Course> courses, String electiveName, int hours, UUID uuid){
        this.courses = courses;
        this.electiveName = electiveName;
        this.hours = hours;
        this.uuid = uuid;
    }

    public void displayName(){
        System.out.println(electiveName);
    }
}
