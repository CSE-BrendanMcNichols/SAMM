package backEnd;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Course {
    private UUID uuid;
    private String courseName;
    private String courseSubject;
    private int courseNumber;
    private ArrayList<Requirement> prerequisites;
    private ArrayList<Requirement> corequisites;
    private ArrayList<Semester> courseAvailability;
    private String courseDescription;
    private int courseHours;
    private char minGrade;
    private CourseState courseStatus;

    public Course(){
        this.courseName = "EMPTY CLASS";
    }

    public Course(String courseName, int courseNumber,
                  ArrayList<Requirement> prerequisites, ArrayList<Requirement> corequisites,
                  ArrayList<Semester> courseAvailability, String courseDescription,
                  int courseHours, char minGrade, CourseState courseStatus,UUID uuid) {

        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.prerequisites = new ArrayList<>(prerequisites);
        this.corequisites = new ArrayList<>(corequisites);
        this.courseAvailability = new ArrayList<>(courseAvailability);
        this.courseDescription = courseDescription;
        this.courseHours = courseHours;
        this.minGrade = minGrade;
        this.courseStatus = courseStatus;
        this.uuid = uuid;
    }
  
    public Course(String courseName, int courseNumber, ArrayList<Semester> courseAvailability, String courseDescription,
            int courseHours, char minGrade, CourseState courseStatus, UUID uuid) {

        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.courseAvailability = new ArrayList<>(courseAvailability);
        this.courseDescription = courseDescription;
        this.courseHours = courseHours;
        this.minGrade = minGrade;
        this.courseStatus = courseStatus;
        this.uuid = uuid;
    }

    //DELETE CODE BELOW AFTER TESTING IT
    public Course(String string, int i, String string2, String string3, String string4) {
        courseName = string;
        courseNumber = i;
        courseDescription = string2;
        Random rng = new Random();
        courseHours = rng.nextInt(15-0+1)+1;
        System.out.println("Course " + courseName + " has " + courseHours + "hours");
        minGrade = string4.charAt(0);
        
        courseStatus = CourseState.NOT_STARTED;
    }
    // DELETE CODE ABOVE AFTER TESTING

    public UUID getUuid() {
        return uuid;
    }

    public String getUuidString() {
        return uuid.toString();
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public ArrayList<Requirement> getPrerequisites() {
        return new ArrayList<>(prerequisites);
    }

    public ArrayList<Requirement> getCorequisites() {
        return new ArrayList<>(corequisites);
    }

    public ArrayList<Semester> getCourseAvailability() {
        return new ArrayList<>(courseAvailability);
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getCourseHours() {
        return courseHours;
    }

    public char getMinGrade() {
        return minGrade;
    }


    public CourseState getCourseStatus() {
        return courseStatus;
    }

    // Setters
    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCourseHours(int courseHours) {
        this.courseHours = courseHours;
    }

    public void setMinGrade(char minGrade) {
        this.minGrade = minGrade;
    }


    public void setCourseStatus(CourseState courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String toString1String(){
        return "Course UUID " + uuid.toString() + "\n" + "Course name:" + courseName + "\nCourse description:" + courseDescription + "\n";
        //return courseName;
    }
    



    
    @Override
    public String toString() {
        return "Course [uuid=" + uuid + ", courseName=" + courseName + ", courseSubject=" + courseSubject
                + ", courseNumber=" + courseNumber + ", prerequisites=" + prerequisites + ", corequisites="
                + corequisites + ", courseAvailability=" + courseAvailability + ", courseDescription="
                + courseDescription + ", courseHours=" + courseHours + ", minGrade=" + minGrade + ", courseStatus="
                + courseStatus + "]";
    }

    // Methods for managing prerequisites and corequisites
    public void addPrerequisite(Requirement prerequisite) {
        if (!prerequisites.contains(prerequisite)) {
            prerequisites.add(prerequisite);
        }
    }

    public void addCorequisite(Requirement corequisite) {
        if (!corequisites.contains(corequisite)) {
            corequisites.add(corequisite);
        }
    }

    public void removePrerequisite(Requirement prerequisite) {
        prerequisites.remove(prerequisite);
    }

    public void removeCorequisite(Requirement corequisite) {
        corequisites.remove(corequisite);
    }

    // Methods for course availability
    public void addSemesterOffered(Semester semester) {
        if (!courseAvailability.contains(semester)) {
            courseAvailability.add(semester);
        }
    }

    public void removeSemesterOffered(Semester semester) {
        courseAvailability.remove(semester);
    }

    

    public void printSemester(){
        for( Semester semester: courseAvailability){
            System.out.println(semester.name());
        }
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setPrerequisites(ArrayList<Requirement> prerequisites) {
        this.prerequisites = prerequisites;
    }
    public void setCorequisites(ArrayList<Requirement> corequisites) {
        this.corequisites = corequisites;
    }
    public void setCourseAvailability(ArrayList<Semester> courseAvailability) {
        this.courseAvailability = courseAvailability;
    }
}