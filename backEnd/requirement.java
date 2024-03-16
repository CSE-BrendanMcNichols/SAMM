package backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class Requirement {
    private Boolean eitherOr;
    private String requirementFor;
    private RequirementType type;
    private ArrayList<Course> courses;
    private UUID uuid;

    Requirement(ArrayList<Course> courses, Boolean eitherOr, RequirementType type, String requirementFor, UUID uuid) {
        this.eitherOr = eitherOr;
        this.requirementFor = requirementFor;
        this.type = type;
        this.courses = courses;
        this.uuid = uuid;
    }

    public void addCourse(Course course) {
        if (course != null) {
            if (courses == null) {
                courses = new ArrayList<Course>();
            }
            courses.add(course);
        }
        else {
            System.out.println("addCourse::Invalid parameter:" + course);
        }
    }

    



    @Override
    public String toString() {
        return "Requirement [eitherOr=" + eitherOr + ", requirementFor=" + requirementFor + ", type=" + type
                 + ", uuid=" + uuid + "]";
    }

    public String toString1() {
        return "Requirement\n Uuid=" + uuid + "\n" + "Courses=" + courses +"]";
    }

    public RequirementType getType() {
        return this.type;
    }

    public Boolean getEitherOr() {
        return this.eitherOr;
    }

    public String getRequirementFor() {
        return this.requirementFor;
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    public void setEitherOr(Boolean eitherOr) {
        this.eitherOr = eitherOr;
    }

    public void setRequirementFor(String requirementFor) {
        this.requirementFor = requirementFor;
    }

    public void setType(RequirementType type) {
        this.type = type;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    
}