package backEnd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.xml.sax.ErrorHandler;

/**
 * This is a Student Class
 * 
 * @Author Sree
 */

/*
 * 3-15 Update log by Matthew Bojanowski
 * Added completedElectives and currentElectives and Elective
 * Added getters and setters for those
 * Added addElective and removeElective
 * Added updateElectivesCompleted
 */
public class Student extends User {
   

    private Year gradeYear = Year.Freshman;
    private Advisor advisor = new Advisor("Default Advisor", "Default Constructor", "Default ID", "Default Email", "Default Username", "Default Password", null, "Default Apartment");
    private Major major = new Major("Default Major");
    private double overallGrade = 0.0;
    
    private int credits = 0;
    private HashMap<Course, String> completedCourses = new HashMap<Course, String>();
    private ArrayList<Course> currentCourses = new ArrayList<Course>();
    private ArrayList<Course> remainingCourses = new ArrayList<Course>();
    private ArrayList<String> notes = new ArrayList<String>();
    private String applicationArea = "Default Application Area";
    private ArrayList<Elective> currentElectives = new ArrayList<Elective>();
    private ArrayList<Elective> completedElectives = new ArrayList<Elective>();
    private UUID uuid;
    
    // Updated Constructor
    public Student(String firstName, String lastName, String uscid, String email, String username, String password,
            Year gradeYear, Advisor advisor, Major major, double overallGrade, int credits,
            HashMap<Course, String> completedCourses, ArrayList<Course> currentCourses, ArrayList<String> notes,
            ArrayList<Elective> currentElectives, ArrayList<Elective> completedElectives, String applicationArea) {
        super(firstName, lastName, uscid, email, username, password, UserType.STUDENT);
        this.gradeYear = gradeYear;
        this.advisor = advisor;
        this.major = major;
        this.overallGrade = overallGrade;
        this.credits = credits;
        if (completedCourses != null)
            this.completedCourses = completedCourses;
        if (currentCourses != null)
            this.currentCourses = currentCourses;
        if (completedElectives != null)
            this.completedElectives = completedElectives;
        if (currentElectives != null)
            this.currentElectives = currentElectives;
        this.notes = notes;
        this.uuid = UUID.randomUUID();
        this.applicationArea = applicationArea;


        if(getFirstName() == null){
            this.setFirstName("Default First Name");
        }
        if(getLastName() == null){
            this.setLastName("Default Last Name");
    
        }
        if(getUsername() == null){
            this.setUsername("Default Username");
        }

    }




    //Default constructor
    //By Matthew
    public Student(){
        super(UserType.STUDENT);
        gradeYear = Year.Freshman;
        advisor = new Advisor("Default Advisor", "Default Constructor", "Default ID", "Default Email", "Default Username", "Default Password", null, "Default Apartment");
        major = new Major("Default Major");
        overallGrade = 0.0;
        credits = 0;
        completedCourses = new HashMap<Course, String>();
        currentCourses = new ArrayList<Course>();
        remainingCourses = new ArrayList<Course>();
        notes = new ArrayList<String>();
        currentElectives = new ArrayList<Elective>();
        completedElectives = new ArrayList<Elective>();
        uuid = UUID.randomUUID();
        applicationArea = "Default Application Area";

    }

    // constructor for DataLoader final run through
    public Student(String firstName, String lastName, String uscid, String email, String username, String password,
            Year gradeYear, Advisor advisor, Major major, double overallGrade, int credits,
            HashMap<Course, String> completedCourses, ArrayList<Course> currentCourses, ArrayList<String> notes,
            UUID uuid, ArrayList<Elective> currentElectives, ArrayList<Elective> completedElectives,
            String applicationArea) {
        super(uuid, firstName, lastName, uscid, email, username, password, UserType.STUDENT);
        this.gradeYear = gradeYear;
        this.advisor = advisor;
        this.major = major;
        this.overallGrade = overallGrade;
        this.credits = credits;
        this.notes = notes;
        this.uuid = uuid;

        if (completedCourses != null)
            this.completedCourses = completedCourses;
        if (currentCourses != null)
            this.currentCourses = currentCourses;
        if (completedElectives != null)
            this.completedElectives = completedElectives;
        if (currentElectives != null)
            this.currentElectives = currentElectives;
        this.applicationArea = applicationArea;
    }

    // constructor for DataLoader first sweep
    public Student(String firstName, String lastName, String uscid, String email, String username, String password,
            Year gradeYear, Major major, double overallGrade, int credits, HashMap<Course, String> completedCourses,
            ArrayList<Course> currentCourses, ArrayList<String> notes, UUID uuid, ArrayList<Elective> currentElectives,
            ArrayList<Elective> completedElectives, String applicationArea) {
        super(uuid, firstName, lastName, uscid, email, username, password, UserType.STUDENT);
        this.gradeYear = gradeYear;
        this.major = major;
        this.overallGrade = overallGrade;
        this.credits = credits;
        this.completedCourses = completedCourses;
        this.currentCourses = currentCourses;
        this.notes = notes;
        this.uuid = uuid;
        this.completedElectives = completedElectives;
        this.currentElectives = currentElectives;
        this.applicationArea = applicationArea;
    }

    // Added Getters and setters methods

    public Student(String firstName, String lastName, String uscid, String email, String username, String password,
            UserType student, Year freshman, Object object, Object object2, double d, int j, Object object3,
            Object object4, Object object5) {
        // TODO Auto-generated constructor stub
    }

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

    public void setRemainingCourses(ArrayList<Course> remainingCourses) {
        this.remainingCourses = remainingCourses;
    }

    public ArrayList<Course> getRemainingCourses() {
        return remainingCourses;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public double calculateGPA() {
        double gpa = 0.0;
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            if (grade.equals("A")) {
                gpa += 4.0;
            } else if (grade.equals("B")) {
                gpa += 3.0;
            } else if (grade.equals("C")) {
                gpa += 2.0;
            } else if (grade.equals("D")) {
                gpa += 1.0;
            } else if (grade.equals("F")) {
                gpa += 0.0;
            }
        }
        return gpa;
    }
    /*
     * Calculates the students gpa
     */

    public void viewClassGrades() {
        if(completedCourses.size() == 0) { 
            System.out.print("Error No Classes");
            return;
        }

        System.out.println("These are your class grades:");
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            String resultgrade;
            String resultsubject = course.getCourseSubject();
            if (grade == null) {
                resultgrade = "Error null is not a Grade";
            } else if(grade.equals("null")){
                resultgrade = "Error null is not a Grade";
            } else{
                resultgrade = grade;
            }

            if(resultsubject == null) {
                resultsubject = "Error Course Subject is Null";
            }

            System.out.println(resultsubject + ", " + resultgrade);
        }
    }

    public boolean riskOfFailure() {
        if (overallGrade < 2.5) {
            return true;
        }
        return false;
    }

    public void viewCurrentSchedule() {
        System.out.println("--------------------------");
        System.out.println("Current Courses:");
        for (Course course : currentCourses) {
            if(course == null){
                System.out.println("Error Null Class");
            }else{
                System.out.println(course.displayCourse());
            }
            
        }
        System.out.println("Current Electives");
        for (Elective elective : this.currentElectives) {
            System.out.println(elective.getName());
            if (elective.getCourses() != null) {
                for (Course course : elective.getCourses()) {
                    if(course == null){
                        System.out.println("Error Null Class");
                    }else{
                        System.out.println(course.displayCourse());
                    }
                    
                }
            }
        }
        System.out.println("--------------------------");

        // System.out.println("End of Current Schedule");
    }


    private void updateGrade(Course course, char grade) {
        completedCourses.put(course, Character.toString(grade));
    }

    public double toPointGrade(String grade) {
        double pointGrade = 0.0;
        if (grade == "A") {
            pointGrade = 4.0;
        } else if (grade == "B") {
            pointGrade = 3.0;
        } else if (grade == "C") {
            pointGrade = 2.0;
        } else if (grade == "D") {
            pointGrade = 1.0;
        } else if (grade == "F") {
            pointGrade = 0.0;
        }else if (grade == "B+") {
            pointGrade = 3.3;
        }else if (grade == "B-") {
            pointGrade = 2.7;
        }else if (grade == "A+") {
            pointGrade = 4.3;
        }else if (grade == "A-") {
            pointGrade = 3.7;
        }else if (grade == "C+") {
            pointGrade = 2.3;
        }else if (grade == "C-") {
            pointGrade = 1.7;
        }else if (grade == "D+") {
            pointGrade = 1.3;
        }else if (grade == "D-") {
            pointGrade = 0.7;
        }
        return pointGrade;
    }
    /*
     * Calculate the students gpa
     * 
     * @return the students gpa
     */

    public void updateOverallGrade() {
        double totalGrade = 0.0;
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            totalGrade += (toPointGrade(grade) * course.getCourseHours());
        }
        this.overallGrade = totalGrade / credits;
        
    }
    /*
     * Updates the students overall grade and prints it out
     * By Matt
     */

    private void updateCredits() {
        this.credits = getCreditsAccumulated();
        // System.out.println("Credits updated to: " + this.credits);
    }
    /*
     * Updates the students credits and prints it out
     * By Matt
     */

    public void checkHours(HashMap<Course, String> completedCourses) {
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

    public void updateCourseCompleted(Course updateCourse, String courseGrade) {
        // System.out.println("updateCourseCompleted called. updateCourse: " +
        // updateCourse);
        this.completedCourses.put(updateCourse, courseGrade);
        for (Course course : this.currentCourses) {
            try {
                if (course.getUuid() == updateCourse.getUuid()) {
                    currentCourses.remove(course);
                    break;
                }
            }
            catch (NullPointerException e){

            }
        }
        updateCredits();
        updateOverallGrade();
        
    }
    /*
     * Updates students completed course
     * By Matt
     */

    public int getCreditsAccumulated() {
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
     * 
     * @return the credits accumulated
     */

    public void updateCurrentCourses(Course course) {
        ArrayList<Course> updatedclasses = getCurrentCourses();
        updatedclasses.add(course);
        setCurrentCourses(updatedclasses);
    }
    /*
     * updates the students current courses
     */

    public ArrayList<Elective> getCurrentElectives() {
        /*
         * System.out.println("Current electives:");
         * for (Elective e : currentElectives) {
         * System.out.println(e.getName());
         * }
         */
        return currentElectives;
    }

    public void setCurrentElectives(ArrayList<Elective> currentElectives) {
        this.currentElectives = currentElectives;
    }

    public ArrayList<Elective> getCompletedElectives() {
        /*
         * System.out.println("Completed electives:");
         * for (Elective e : completedElectives) {
         * System.out.println(e.getName());
         * }
         */

        return completedElectives;
    }

    public void setCompletedElectives(ArrayList<Elective> completedElectives) {
        this.completedElectives = completedElectives;
    }

    public void addElective(Elective elect) {
        if (currentElectives != null) {
            currentElectives.add(elect);
            // System.out.println("Added Elective: " + elect.getName());
        }
    }

    public void removeElective(Elective elect) {

        // Remove by name. in case if the objects are not fully loaded it may not be a
        // good match
        if (this.currentElectives != null) {
            for (Elective elective : this.currentElectives) {
                if (elective.getName().equals(elect.getName())) {
                    this.currentElectives.remove(elect);
                    // System.out.println("Removed Elective: " + elect.getName());
                    break;
                }
            }
        }
    }

    public void updateElectiveCompleted(Elective elect) {

        removeElective(elect);

        if (this.completedElectives != null) {
            this.completedElectives = new ArrayList<Elective>();
            this.completedElectives.add(elect);
            // System.out.println("Elective: " + elect.getName() + " is added tp completed
            // electives list");
        }
    }

    public void setApplicationArea(String area) {
        applicationArea = area;
    }

    public String getApplicationArea() {
        return applicationArea;
    }

    public String getAdvisorName() {
        if (advisor != null) {
            return advisor.getFirstName() + " " + advisor.getLastName();
        } else {
            return "No Advisor assigned";
        }
    }

    public void addNotes(String note) {
        if (notes == null) {
            this.notes = new ArrayList<String>();
        }
        notes.add(note);
    }

    public String displayStudent() {
        return "Student: " + this.getUscid() + " : " + this.getFirstName() + " " + this.getLastName();
    }
    public static void checkProgress(Student student) {
        System.out.println("\n" + student.getFirstName() + " " + student.getLastName() + "'s Completed Courses: ");
        System.out.println("------------------------------------");
        
        HashMap<Course, String> test = student.getCompletedCourses();
        for (Map.Entry<Course, String> entry : test.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            System.out.println(course.getCourseName() + " " + course.getCourseNumber() + " Grade: " + grade);
        }
        
        System.out.println("\n"+ student.getFirstName() + " " + student.getLastName() +"'s Remaining Courses:");
        System.out.println("------------------------------------");
        try {
        for (Course course : student.getCurrentCourses()) {
            System.out.println(course.getCourseName() + " " + course.getCourseNumber());
        }
    } catch (NullPointerException e){

    }
        
    }
    /*
     * Used to have error where program didnt compile.
     * Fixed by realizing that only students call this and changed parameter to a student
     */

    public static void generateSemesterPlan(User user) {
        try {

            if(user.getType() != UserType.STUDENT){
                return;
            }
            Student student = UserList.getInstance().getStudent(user.getUuid());
            FileWriter writer = new FileWriter("backEnd/txt/" + student.getFirstName() + student.getLastName() + "_SemesterPlan.txt");
            writer.write(student.getFirstName() + " " + student.getLastName() + "'s 8-Semester Plan:\n\n");
            ArrayList<Course> coursesToTake = new ArrayList<>(student.getCurrentCourses());
            for (int i = 1; i <= 8; i++) {
                writer.write("Semester " + i + ":\n");
                writer.write("----------\n");
                writer.write("Courses to Take:\n");
                // TODO: fix the logic
                for (Course course : coursesToTake) {
                    writer.write(course.getCourseSubject() + " " + course.getCourseNumber() + " - " + course.getCourseName() + "\n");
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("\n8-Semester Plan for " + student.getFirstName() + " " + student.getLastName() + "has been generated and saved to " + 
            student.getFirstName() + student.getLastName() + "SemesterPlan.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the 8-Semester Plan to a file.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Student [gradeYear=" + gradeYear + ", advisor=" + advisor + ", major=" + major
                + ", overallGrade="
                + overallGrade + ", credits=" + credits + ", completedCourses=" + completedCourses + ", currentCourses="
                + currentCourses + ", notes=" + notes + ", applicationArea=" + applicationArea + ", currentElectives="
                + currentElectives + ", completedElectives=" + completedElectives + ", uuid=" + uuid + "]";
    }

}




/*
 * Tests passed or failed in studenttester:
 * ones with no extra text havent been tested yet
 * tests that pass will be deleted from this list
 68
 75
 84
 95
 108
 121
 134
 146
 158
 170
 184
 192
 203
 217
 228
 240
 252
 264
 276
 282
 288
 294
 312
 320
 332
 341
 348
 356
 363
 373
 382
 391
 399
 408
 419
 428
 436
 448
 455
 464
 472
 482
 489
 495
 506
 522
 534
 544
 562-???
 592
 618
 643-???
 673-error
 692-error
 704-error
 724-error
738-error
751
771-error
799
816-error
833
 */