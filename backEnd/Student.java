package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This is a Student Class
 * 
 * @Author Sree
 */


 /*
 3-15 Update log by Matthew Bojanowski
Added completedElectives and currentElectives
Added getters and setters for those
Added addElective and removeElective
Added updateElectivesCompleted
*/
 public class Student extends User {
    private Year gradeYear;
    private Advisor advisor;
    private Major major;
    private double overallGrade;
    private int credits;
    private HashMap<Course, String> completedCourses;
    private ArrayList<Course> currentCourses;
    private ArrayList<String> notes;
    private ArrayList<Elective> currentElectives;
    private ArrayList<Elective> completedElectives;

    // Updated Constructor
    public Student(String firstName, String lastName, String uscid, String email, String username, String password, UserType type, Year gradeYear, Advisor advisor, Major major, double overallGrade, int credits, HashMap<Course, String> completedCourses, ArrayList<Course> currentCourses, ArrayList<String> notes) {
        super(firstName, lastName, uscid, email, username, password, type);
        this.gradeYear = gradeYear;
        this.advisor = advisor;
        this.major = major;
        this.overallGrade = overallGrade;
        this.credits = credits;
        this.completedCourses = completedCourses;
        this.currentCourses = currentCourses;
        this.notes = notes;
    }
    
    // Added Getters and setters methods
    
    public Year getGradeYear() {
        return gradeYear;
    }

    public void setGradeYear(Year gradeYear) {
        this.gradeYear = gradeYear;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public double getOverallGrade() {
        return overallGrade;
    }

    public void setOverallGrade(double overallGrade) {
        this.overallGrade = overallGrade;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public HashMap<Course, String> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(HashMap<Course, String> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public ArrayList<Course> getCurrentCourses() {
        return currentCourses;
    }

    public void setCurrentCourses(ArrayList<Course> currentCourses) {
        this.currentCourses = currentCourses;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    private double calculateGPA(){
        double gpa = 0.0;
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            if(grade.equals("A")){
                gpa += 4.0;
            }else if(grade.equals("B")){
                gpa += 3.0;
            }else if(grade.equals("C")){
                gpa += 2.0;
            }else if(grade.equals("D")){
                gpa += 1.0;
            }else if(grade.equals("F")){
                gpa += 0.0;
            }
        }
        return gpa;
    }
    /*
     * Calculates the students gpa
     */

    private void viewClassGrades(){
        System.out.println("These are your class grades:");
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            System.out.println(course.getCourseSubject() + ", " + grade);
        }
    }
    public boolean riskOfFailure(){
        if(overallGrade < 2.5){
            return true;
        }
        return false;
    }
    public Major viewFutureSchedule(){
        major.getCourses();
        return null;
    }
    public void viewCurrentSchedule(){
        System.out.println("This is Your Current Schedule:");
        for(Course course: this.currentCourses){
            System.out.println(course);
        }
    }
    private void assignAdvisor(Advisor advisor){
        this.advisor = advisor;
    }
    private void updateGrade(Course course, char grade){
        completedCourses.put(course, Character.toString(grade));
    }

    
    private double toPointGrade(String grade){
        double pointGrade = 0.0;
        if(grade == "A"){
            pointGrade = 4.0;
        }else if(grade == "B"){
            pointGrade = 3.0;
        }else if(grade == "C"){
            pointGrade = 2.0;
        }else if(grade == "D"){
            pointGrade = 1.0;
        }else if(grade == "F"){
            pointGrade = 0.0;
        }
        return pointGrade;
    }
    /*
     * Calculate the students gpa
     * @return the students gpa
     */

    public void updateOverallGrade(){
        double totalGrade = 0.0;
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            totalGrade += (toPointGrade(grade) * course.getCourseHours());
        }
        this.overallGrade = totalGrade/credits;
        System.out.println(this.overallGrade);
    }
    /*
     * Updates the students overall grade and prints it out
     * By Matt
     */

    
    private void updateCredits(){
        this.credits = getCreditsAccumulated();
        System.out.println("Credits updated to: " + this.credits);
    }
    /*
     * Updates the students credits and prints it out
     * By Matt
     */

    
    public void checkHours(HashMap<Course, String> completedCourses){
        int creditTotal = 0;
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            creditTotal += course.getCourseHours();
        }
        System.out.println("credit hours completed are: " + creditTotal);
    }
    /*
     * Checks the hours of completed courses and prints them out
     * By Matt
     */

    
    public void updateCourseCompleted(Course updateCourse, String courseGrade){
        System.out.println("updateCourseCompleted called. updateCourse: " + updateCourse);
        for(Course course: this.currentCourses){
            if(course.getUuid() == updateCourse.getUuid()){
                currentCourses.remove(course);
            }
        }
        this.completedCourses.put(updateCourse, courseGrade);
        updateCredits();
        updateOverallGrade();
    }
    /*
     * Updates students completed course
     * By Matt
     */

   
    private int getCreditsAccumulated(){
        int creditTotal = 0;
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            creditTotal += course.getCourseHours();
        }
        return creditTotal;
    }
    /*
     * gets the credits accumulated
     * @return the credits accumulated
     */

    
    private void updateCurrentCourses(Course course){
        ArrayList<Course> updatedclasses = currentCourses;
        updatedclasses.add(course);
        setCurrentCourses(updatedclasses);
    }
    /*
     * updates the students current courses
     */


     
public ArrayList<Elective> getCurrentElectives(){
    return currentElectives;
}
public void setCurrentElectives(ArrayList<Elective> currentElectives){
    this.currentElectives = currentElectives;
}
public ArrayList<Elective> getCompletedElectives(){
    return completedElectives;
}
public void setCompletedElectives(ArrayList<Elective> completedElectives){
    this.completedElectives = completedElectives;
}
public void addElective(Elective elect){
    this.currentElectives.add(elect);
}
public void removeElective(Elective elect){
    this.currentElectives.remove(elect);
}

public void updateElectiveCompleted(Elective elect){
    this.currentElectives.remove(elect);
    this.completedElectives.add(elect);
}


}
