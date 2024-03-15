package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
     * this is dummy constructor. delete this after implementing the full
     * constructor
     * 
     * @param major
     */
    public Major(String major) {
        this.major = major;
    }

    /**
     * This is the constructor
     * 
     * @param courses
     * @param coreReq
     * @param electiveCourses
     * @param major
     * @param uuid
     */
    public Major(ArrayList<Course> courses, HashMap<RequirementType, Integer> coreReq, Elective electiveCourses,
            String major, UUID uuid) {
        if (courses == null)
            courses = new ArrayList<Course>();
        else
            this.courses = courses;

        if (coreReq == null)
            coreReq = new HashMap<RequirementType, Integer>();
        else
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
    /*
     * + Major(ArrayList<Course> courses, HashMap<RequirementType, int> coreReq,
     * Elelctive electiveCourses, String major) Major
     * + addCourse(Course course): void
     * + addCoreReq(): void
     * + addElective(Course course): void
     * + toString(): String
     * + printRoadmap(): void
     * - removeCourse(Course course)
     */

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("addCourse called");
    }

    public void addCoreReq() {
        // TODO: ?
        System.out.println("addCoreReq called");
    }

    public void addElective(Course course) {
        // electiveCourses.add(course);
        System.out.println("addElective called");
    }

    public String toString() {
        return "toString called. String: " + major;
    }

    public void printRoadmap() {
        System.out.println("printRoadmap called");
    }

    private void removeCourse(Course course) {
        // Todo:
        System.out.println("removeCourse called");
    }

    public void displaycoreReq() {
        for (Map.Entry<RequirementType, Integer> entry : coreReq.entrySet()) {
            RequirementType key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println(key + " " + value);
        }
    }

    public static Boolean findMajor(ArrayList<Major> majors, UUID uuid) {
        for (Major major : majors) {
            if (major.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public static Major getMajor(ArrayList<Major> majors, UUID uuid) {
        for (Major major : majors) {
            if (major.getUuid().equals(uuid)) {
                return major;
            }
        }
        return null;
    }
}
