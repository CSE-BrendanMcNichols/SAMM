package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * This is a Major class
 * 
 * @author Sree
 */
public class Major {

    private ArrayList<Course> courses;
    private HashMap<RequirementType, Integer> coreReq;
    private Elective electiveCourses;
    private String major;
    private UUID uuid;

    /**
     * this is dummy constructor. delete this after implementing the full constructor
     * @param major
     */
    public Major (String major) {
        this.major = major;
    }
  

    /**
     * This is the constructor
     * @param courses
     * @param coreReq
     * @param electiveCourses
     * @param major
     * @param uuid
     */
    public Major(ArrayList<Course> courses, HashMap<RequirementType, Integer> coreReq, Elective electiveCourses,
            String major, UUID uuid) {
        this.courses = courses;
        this.coreReq = coreReq;
        this.electiveCourses = electiveCourses;
        this.major = major;
        this.uuid = uuid;
    }


    public ArrayList<Course> getCourses() {
        return courses;
    }


    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }


    public HashMap<RequirementType, Integer> getCoreReq() {
        return coreReq;
    }


    public void setCoreReq(HashMap<RequirementType, Integer> coreReq) {
        this.coreReq = coreReq;
    }


    public Elective getElectiveCourses() {
        return electiveCourses;
    }


    public void setElectiveCourses(Elective electiveCourses) {
        this.electiveCourses = electiveCourses;
    }


    public String getMajor() {
        return major;
    }


    public void setMajor(String major) {
        this.major = major;
    }


    public UUID getUuid() {
        return uuid;
    }


    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    // TODO - implement the rest of the methods from the UML diagram


    
}
