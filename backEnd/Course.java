package backEnd;

import java.util.ArrayList;
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
    private CourseState courseSatus;
    private UUID uuid = UUID.randomUUID();

    Course(String courseName, Semester courseSemester, int courseNumber,
            ArrayList<Requirement> prerequisites, ArrayList<Requirement> corequisites,
            String courseDescription, int courseHours, char minGrade, char userGrade,
            CourseState courseSatus) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseNumber = courseNumber;
        this.prerequisites = prerequisites;
        this.corequisites = corequisites;
        this.courseDescription = courseDescription;
        this.courseHours = courseHours;
        this.minGrade = minGrade;
        this.userGrade = userGrade;
        this.courseSatus = courseSatus;
    }

    public void addPrerequisite(Requirement requirement) {

    }

    public void addPreOrCoRequisite(Requirement requirement) {

    }

    public void addCorequisite(Requirement requirement) {

    }

    public String displayDescription(){
        return this.courseDescription;
    }

    public String toString(){
        return "";
    }
}
