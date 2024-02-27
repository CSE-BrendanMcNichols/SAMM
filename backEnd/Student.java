package backEnd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This is a Student Class
 * 
 * @Author Sree
 */
public class Student extends User {
    private Year gradeYear;
    private Advisor advisor;
    private Major major;
    private double overallGrade;
    private int credits;
    private ArrayList<Course> completedClasses;
    private ArrayList<Course> currentClasses;
    private ArrayList<String> notes;

    // TODO - implement the rest of the methods from the UML diagram

    public Student(String username, String password, String email, String uscid) {
        super(username, password, email, uscid);
    }

    public Student(String username, String password, String email, String uscid, UUID uuid) {
        super(username, password, email, uscid, uuid);
    }

    public Student(String username, String password, String email, String uscid, Year gradeYear,
            Advisor advisor, Major major, double overallGrade, int credits,
            ArrayList<Course> completedCourses, ArrayList<Course> currentCourses, ArrayList<String> notes) {
        super(username, password, email, uscid);
        this.major = major; 
        this.gradeYear = gradeYear; 
        this.advisor = advisor; 
        this.overallGrade = overallGrade; 
        this.credits = credits; 
        this.completedClasses = completedCourses; 
        this.currentClasses = currentCourses; 
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

    public ArrayList<Course> getCompletedClasses() {
        return completedClasses;
    }

    public void setCompletedClasses(ArrayList<Course> completedClasses) {
        this.completedClasses = completedClasses;
    }

    public ArrayList<Course> getCurrentClasses() {
        return currentClasses;
    }

    public void setCurrentClasses(ArrayList<Course> currentClasses) {
        this.currentClasses = currentClasses;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }




    private double calculateGPA(ArrayList<Course> courses){
        //Take credit hours for class and grade for it and calculate its gpa
        for(int i = 0; i < getCompletedClasses().size(); i++){
            System.out.println(getCompletedClasses().get(i));
        }
        return 2.9;
    }
    private ArrayList<Course> viewClassGrades(){

    }
    public boolean riskOfFailure(){

    }
    public Major viewFutureSchedule(){
        
    }
    public ArrayList<Course> viewCurrentSchedule(){
        
    }
    private void assignAdvisor(Advisor advisor){
        this.advisor = advisor;
    }
    private void updateGrade(Course course, char grade){

    }
    private void checkHours(ArrayList<Course> completedCourses){

    }
    private void updateCourseCompleted(Course course){

    }
    private int getCreditsAccumulated(){

    }
    private void updateCurrentCourses(Course course){
        
    }
}
