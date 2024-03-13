package backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class Administrator extends User {

    Student student;

    public Administrator(String username, String password, String email, String uscid, UUID uuid) {
        super(username, password, email, uscid, uuid);
    }

    private void overrideStudentGrade(String name, char grade) {
        System.out.println("The new grade for " + name + "will be " + grade);
    }

    private void updateMajor(String majorDetails) {
        System.out.println("The new major is " + majorDetails);
    }

    private void overrideCourseReq(Course course, ArrayList<Course> newPreReq) {
        //TODO implement logic
    }

    public boolean riskOfFaliure(Student student) {
        if(student.riskOfFailure()) {
            return true;
        }
        
        return false;
    }

    private void createCourse(String courseName,Semester courseSemester, int courseNumber, ArrayList<Requirement> prerequisites, ArrayList<Requirement> corequisites, String courseDescription) {
        //TODO add logic into create Course
    }

}
