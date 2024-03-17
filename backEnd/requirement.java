package backEnd;
import java.util.ArrayList;
import java.util.UUID;
public class Requirement{
    private Boolean eitherOr;
    private String requirementFor;
    private RequirementType type;
    private ArrayList<Course> courses;
    private UUID uuid;
    Requirement(ArrayList<Course> courses, Boolean eitherOr, RequirementType type, String requirementFor, UUID uuid){
        this.eitherOr = eitherOr;
        this.requirementFor = requirementFor;
        this.type = type;
        this.courses = courses;
        this.uuid = uuid;
    }

    public UUID getUuid(){
        return this.uuid;
    }

    public static Boolean findReq(ArrayList<Requirement> requirements, UUID uuid ){
        for (Requirement requirement : requirements){
            if(requirement.getUuid().equals(uuid)){
                return true;
            }
        }
        return false;
    }
    public static Requirement getReq(ArrayList<Requirement> requirements, UUID uuid ){
        for (Requirement requirement : requirements){
            if(requirement.getUuid().equals(uuid)){
                return requirement;
            }
        }
        return null;
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

    public void displayRequirement(Requirement requirement){
        System.out.println("These are the courses that satisfy the Requirement: "+ type);
        for(Course course: courses){
            System.out.println(course.displayCourse());
        }
    }
}