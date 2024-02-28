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
    private void viewClassGrades(){
        System.out.println("These are your class grades:");
        for(Course course: this.completedClasses){
            System.out.println(course.getName()+": "+course.getUserGrade());
        }
    }
    public boolean riskOfFailure(){
        if(overallGrade < 2.5){
            return true;
        }
        return false;
    }
    public Major viewFutureSchedule(){
        
    }
    public void viewCurrentSchedule(){
        System.out.println("This is Your Current Schedule:");
        for(Course course: this.currentClasses){
            System.out.println(course);
        }
    }
    private void assignAdvisor(Advisor advisor){
        this.advisor = advisor;
    }
    private void updateGrade(Course course, char grade){
        course.setUserGrade(grade);
    }
    private void updateOverallGrade(){
        double totalGrade = 0.0;
        for(Course course: this.completedClasses){
            totalGrade += (course.getPointGrade() * course.getCourseHours());
        }
        this.overallGrade = totalGrade/credits;
    }
    private void updateCredits(){
        this.credits = getCreditsAccumulated();
    }
    private void checkHours(ArrayList<Course> completedCourses){
        int creditTotal = 0;
        for(Course course: this.completedClasses){
            creditTotal += course.getCourseHours();
        }
        System.out.println("credit hours completed are: " + creditTotal);
    }
    private void updateCourseCompleted(Course updateCourse){
        for(Course course: this.currentClasses){
            if(course.getUuid() == updateCourse.getUuid()){
                currentClasses.remove(course);
            }
        }
        this.completedClasses.add(updateCourse);
        updateCredits();
        updateOverallGrade();
    }
    private int getCreditsAccumulated(){
        int creditTotal = 0;
        for(Course course: this.completedClasses){
            creditTotal += course.getCourseHours();
        }
        return creditTotal;
    }
    private void updateCurrentCourses(Course course){
        
    }
}
