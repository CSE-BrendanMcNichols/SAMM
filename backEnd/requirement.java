package backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class Requirement{
    private Boolean eitherOr;
    private String requirementFor;
    private RequirementType type;
    private ArrayList<Course> courses;

    Requirement(ArrayList<Course> courses, Boolean eitherOr, RequirementType type, String requirementFor){
        this.eitherOr = eitherOr;
        this.requirementFor = requirementFor;
        this.type = type;
        this.courses = courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public String toString(){
        return"description";
    }

    public RequirementType getType(){
        return this.type;
    }

    public Boolean getEitherOr(){
        return this.eitherOr;
    }

    public String getRequirementFor(){
        return this.requirementFor;
    }

    public ArrayList<Course> getCourses(){
        return this.courses;
    }
}
