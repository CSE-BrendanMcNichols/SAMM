package backEnd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Modification History
 * 
 * 1) Sree - 03/13 - implemented searchStudent, updateStudentGrade, updateStudentCredits, riskOfFailure, makeNote
 * //See TODO for pending stub methods
 * 
 * 
 */

/**
 * This is a Advisor class
 * 
 * @author sree
 */
public class Advisor extends User {

    private ArrayList<Student> assignedStudents;

    /**
     * Constuctor
     * 
     * @param username
     * @param password
     * @param email
     * @param uscid
     * @param uuid
     */
    public Advisor(String username, String password, String email, String uscid, UUID uuid) {
        super(username, password, email, uscid, uuid);
    }

    /**
     * Constructor with Assigned Students
     * 
     * @param username
     * @param password
     * @param email
     * @param uscid
     * @param uuid
     * @param assignedStudents
     */
    public Advisor(String username, String password, String email, String uscid, UUID uuid,
            ArrayList<Student> assignedStudents) {
        super(username, password, email, uscid, uuid);
        this.assignedStudents = assignedStudents;
    }

    /**
     * Getter method for assignedStudents
     * 
     * @return
     */
    public ArrayList<Student> getAssignedStudents() {
        return assignedStudents;
    }

    /**
     * Setter methods for assigned Students
     * 
     * @param assignedStudents
     */
    public void setAssignedStudents(ArrayList<Student> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }

    /**
     * This methods returns the matching Student object
     * 
     * @param IdOrName
     * @param name
     * @return
     */
    public Student searchStudent(String IdOrName, String name) {

        for (Student student : assignedStudents) {
            if (student.getUsername().equals(IdOrName) || student.getUsername().equals(name)) {
                System.out.println("Returning Matching Student ");
                return student;
            }
        }
        System.out.println("It looks like : " + IdOrName + " or " + name
                + " is not an assinged for this Advisor: " + this.getUsername());
        return null;

    }

    /**
     * This methods updates the grade for the given student
     * 
     * @param student
     * @param course
     * @param grade
     */
    public void updateStudentGrade(Student pStudent, Course pCourse, char pGrade) {

        Student student = findAssignedStudent(pStudent);
        Course course = findStudentCourse(student, pCourse);
        if (course != null) {
            course.setUserGrade(pGrade);
            System.out.println(
                    "Successfully updated Student: " + student.getUsername() + "'s grade with " + pGrade
                            + " for the course:"
                            + course.getName());
        }

    }

    /**
     * This method updates the student Credits
     */
    public void updateStudentCredits(Student pStudent, Course pCourse, int credits) {

        Student student = findAssignedStudent(pStudent);
        if (student != null) {
            student.setCredits(credits);
            System.out.println("Succesfully updated Student: " + student.getUsername() + "'s credits with " + credits);
        }
    }

    /**
     * Checks if the given student has a risk of failing
     * 
     * @param username
     * @return
     */
    public boolean riskOfFailure(String username) {

        Student student = findAssignedStudent(username);
        // TODO : stub method - temporary logic
        if (student.getCredits() < 1) {
            return true;
        }
        return false;
    }

    /**
     * Makes notes for the given student
     * 
     * @param username
     * @return
     */
    public void makeNote(Student pStudent, String note) {

        Student student = findAssignedStudent(pStudent);
        if (student != null) {
            ArrayList<String> notes = student.getNotes();
            if (notes == null) {
                notes = new ArrayList<String>();
            }
            notes.add(note);
            // TODO: instead of doing all of these things here, correct the Student.setNotes
            // method to take a note string and append the new notes in the setNotes method
            student.setNotes(notes);
        }
    }

    public void trackStudents() {
        // TODO: stub method
    }

    /**
     * Returns the matching student
     * Helper method
     * 
     * @param pStudent
     * @return
     */
    private Student findAssignedStudent(Student pStudent) {
        if (pStudent != null) {
            return findAssignedStudent(pStudent.getUsername());
        }
        return null;
    }

    /**
     * Returns the matching student
     * Helper method
     * 
     * @param pStudent
     * @return
     */
    private Student findAssignedStudent(String username) {
        boolean matchingStudentFound = false;
        for (Student student : assignedStudents) {
            if (student.getUsername().equals(username)) {
                matchingStudentFound = true;
                return student;
            }
        }
        if (!matchingStudentFound) {
            System.out.println("It looks like Student: " + pStudent.getUsername()
                    + " is not an assinged a student for this Advisor: " + this.getUsername());
        }
        return null;
    }

    /**
     * Returns the matching student
     * Helper method
     * 
     * @param pStudent
     * @param pCourse
     * @return
     */
    private Course findStudentCourse(Student pStudent, Course pCourse) {

        boolean matchingCourseFound = false;
        for (Course course : pStudent.getCurrentClasses()) {
            if (course.getCourseNumber() == pCourse.getCourseNumber()) {
                matchingCourseFound = true;
                // Found the matching Course
                return course;
            }
        }
        if (!matchingCourseFound) {
            System.out.println("It looks like the Course: " + pCourse.getName()
                    + " is not an assinged to Student: " + pStudent.getUsername());
        }
        return null;
    }

}
