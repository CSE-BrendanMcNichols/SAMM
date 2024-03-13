package backEnd;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Course {
    private String courseName;
    private Semester courseSemester;
    private int courseNumber;
    private ArrayList<Requirement> prerequisites;
    private ArrayList<Requirement> corequisites;
    private String courseDescription;
    private int courseHours;
    private char minGrade;
    private char userGrade;
    private CourseState courseStatus;
    private UUID uuid = UUID.randomUUID();

    Course(String courseName, Semester courseSemester, int courseNumber,
            ArrayList<Requirement> prerequisites, ArrayList<Requirement> corequisites,
            String courseDescription, int courseHours, char minGrade, char userGrade,
            CourseState courseStatus) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseNumber = courseNumber;
        this.prerequisites = prerequisites;
        this.corequisites = corequisites;
        this.courseDescription = courseDescription;
        this.courseHours = courseHours;
        this.minGrade = minGrade;
        this.userGrade = userGrade;
        this.courseStatus = courseStatus;
    }
    Course(String courseName, Semester courseSemester, int courseNumber,
            String courseDescription, int courseHours, char minGrade, char userGrade,
            CourseState courseStatus, UUID uuid) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseNumber = courseNumber;
        this.prerequisites = new ArrayList<Requirement>();
        this.corequisites = new ArrayList<Requirement>();
        this.courseDescription = courseDescription;
        this.courseHours = courseHours;
        this.minGrade = minGrade;
        this.userGrade = userGrade;
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
        userGrade = string4.charAt(1);
        courseStatus = CourseState.NOT_STARTED;

    }
    //DELETE CODE ABOVE AFTER TESTING IT
    public void addPrerequisite(Requirement requirement) {
        this.prerequisites.add(requirement);
    }

    public void addPreOrCoRequisite(Requirement requirement) {
        //this.prerequisites.add(requirement);
    }

    public void addCorequisite(Requirement requirement) {
        this.corequisites.add(requirement);
    }
    public String getName(){
        return this.courseName;
    }
    public char getUserGrade(){
        return this.userGrade;
    }
    public void setUserGrade(char userGrade){
        this.userGrade = userGrade;
    }

    public String displayDescription(){
        return this.courseDescription;
    }

    public String toString(){
        return "Course name:" + courseName + " Course description:" + courseDescription + " Course grade:" + userGrade + "\n";
        //return courseName;
    }

    public int getCourseHours(){
        return this.courseHours;
    }

    public UUID getUuid(){
        return this.uuid;
    }

    public int getCourseNumber() {
        return this.courseNumber;
    }

    public double getPointGrade(){
        double grade = 0.0;
        if(this.userGrade == 'A'){
            grade = 4.0;
        }else if(this.userGrade == 'B'){
            grade = 3.0;
        }else if(this.userGrade == 'C'){
            grade = 2.0;
        }else if(this.userGrade == 'D'){
            grade = 1.0;
        }else if(this.userGrade == 'F'){
            grade = 0.0;
        }
        return grade;
    }


}
