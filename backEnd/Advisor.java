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
    private UUID uuid;

    /**
     * Constuctor
     */
    public Advisor(String firstName, String lastName, String uscid, String email, String username, String password) {
        super(firstName, lastName, uscid, email, username, password, UserType.ADVISOR);
        this.assignedStudents = new ArrayList<Student>();
    }

    public Advisor(){
        super();
        this.assignedStudents = new ArrayList<Student>();
    }

    /**
     * Constructor with Assigned Students
     * 
     */
    public Advisor(String firstName, String lastName, String uscid, String email, String username, String password,
            ArrayList<Student> assignedStudents) {
        super(firstName, lastName, uscid, email, username, password, UserType.ADVISOR);
        if (assignedStudents != null) {
            this.assignedStudents = assignedStudents;
        } else
            this.assignedStudents = new ArrayList<Student>();

    }

    public Advisor(String firstName, String lastName, String uscid, String email, String username, String password,
            ArrayList<Student> assignedStudents, UUID uuid) {
        super(uuid, firstName, lastName, uscid, email, username, password, UserType.ADVISOR);
        if (assignedStudents != null) {
            this.assignedStudents = assignedStudents;
        } else
            this.assignedStudents = new ArrayList<Student>();
        //this.uuid = uuid;
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
     * Assign one student
     * 
     * @param student
     */
    public void assignStudent(Student student) {
        this.assignedStudents.add(student);
    }

    /**
     * Assign one student
     * 
     * @param student
     */
    public void unAssignStudent(Student pStudent) {
        for (Student student : this.assignedStudents) {
            if (student.getUuid().equals(pStudent.getUuid())) {
                this.assignedStudents.remove(student);
                System.out.println(
                        "Unassigned the Student: " + pStudent.getUsername() + " from advisor" + this.getUsername());
                break;
            }
        }
        
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
        System.out.println("It looks like Student: " + IdOrName + " or " + name
                + " is not an assinged for Advisor: " + this.getUsername());
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
        if(pStudent == null || pCourse == null) {
            System.out.println(
                    "updateStudentGrade::Invalid Student " + pStudent + " or Invalid Course " + pCourse);
            return;
        }

        Student assignedStudent = findAssignedStudent(pStudent);
        Course studentCourse = findStudentCourse(assignedStudent, pCourse);
        if (studentCourse != null) {
            // course.setUserGrade(pGrade);
            System.out.println(
                    "updateStudentGrade::Successfully updated Student: " + assignedStudent.getUsername() + "'s grade with " + pGrade
                            + " for the course:"
                            + studentCourse.getCourseName());
        }

    }

    /**
     * This method updates the student Credits
     */
    public void updateStudentCredits(Student pStudent, Course pCourse, int credits) {
        if(pStudent == null) {
            System.out.println(
                    "updateStudentCredits::Invalid Student " + pStudent);
            return;
        }

        Student student = findAssignedStudent(pStudent);
        if (student != null) {
            student.setCredits(credits);
            System.out.println("updateStudentCredits::Succesfully updated Student: " + student.getUsername() + "'s credits with " + credits);
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
        if(student == null) {
            System.out.println(
                    "riskOfFailure::Student: " + username + " is not an assignedStudent" );
            return false;
        }

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

        if(pStudent == null) {
            System.out.println(
                    "makeNote::Invalid Student: " + pStudent );
            return;
        }

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
        else {
            System.out.println(
                "makeNote::Student: " + pStudent.getUsername() + " is not an assignedStudent" );
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
            System.out.println("It looks like Student: " + username
                    + " is not an assinged for Advisor: " + this.getUsername());
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
        for (Course course : pStudent.getCurrentCourses()) {
            if (course.getCourseNumber() == pCourse.getCourseNumber()) {
                matchingCourseFound = true;
                // Found the matching Course
                return course;
            }
        }
        if (!matchingCourseFound) {
            System.out.println("It looks like the Course: " + pCourse.getCourseName()
                    + " is not an assinged to Student: " + pStudent.getUsername());
        }
        return null;
    }

    public void displayStudents() {
        for(Student student : assignedStudents) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }

    public static Boolean findAdvisor(ArrayList<Advisor> advisors, UUID uuid){
        for (Advisor advisor : advisors){
            if(advisor.getUuid().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public static Advisor getAdvisor(ArrayList<Advisor> advisors, UUID uuid){
        for (Advisor advisor : advisors){
            if(advisor.getUuid().equals(uuid)){
                return advisor;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Advisor [UUID:" + uuid + "\n"+  super.toString() + "]";
    }

    
}
