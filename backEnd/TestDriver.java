package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;

public class TestDriver {
    private ApplicationFacade facade;

    TestDriver(){
        //facade = new SchedulingFacade;
    }
    public void run(){
        scenario1();
        scenario2();
    }
    public static void main(String[] args) {
        //scenario1();
        scenario2();

        

    }
    public static void scenario1(){

        Student braxWest = new Student("Brax", "West", "12345", "brax@email.sc.edu", "brax_west", "password", 
        UserType.STUDENT, Year.Junior, null, null, 0.0, 0, null, null, null);
        
        checkProgress(braxWest);
        generateSemesterPlan(braxWest);
    }
    public static void scenario2(){
        //scenario 2 code
        Course c1 = new Course("CS101", 3, "Spring 2023", "TTh 10:30-11:45", "Instructor 1");
        Course c2 = new Course("CS202", 3, "Fall 2022", "MWF 10:00-10:50", "Instructor 2");
        Course c3 = new Course("C3", 3, "Fall 2022", "MWF 10:00-10:50", "Instructor 2");
        ArrayList<Course> current = new ArrayList<Course>();
        current.add(c1);
        current.add(c2);
        Elective Stats1 = new Elective(current, "Stats1", 4, null);
        Elective Stats2 = new Elective(current, "Stats2", 3, null);
        Student s1 = new Student("Twanie", "Hill", "111", "email@email.com", "null", "pass", UserType.STUDENT, Year.Junior, null, null, 0, 0, null, null, null);
        
        s1.setApplicationArea("Not Stats");
        
        s1.setCurrentCourses(current);
        HashMap<Course, String> complete = new HashMap<Course, String>();
        complete.put(c3, "Passed");
        s1.setCompletedCourses(complete);
        s1.addElective(Stats1);
        s1.addElective(Stats2);

        //Start the program
        System.out.println("Welcome. Please enter your username to log in.");
        System.out.println("If you wish to create a new account, please type NEWACCOUNT.");
        System.out.println("If you wish to quit the program, please type QUIT.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(input.equals("NEWACCOUNT")){
            System.out.println("Please enter your account username.");
            String newuserusername = scanner.nextLine();
            System.out.println("Please enter your account password.");
            String newuserpassword = scanner.nextLine();
            //Logic to add it to dataloader
            
            Advisor a1 = new Advisor();
            a1.setUsername(newuserusername);
            a1.setPassword(newuserpassword);
            a1.assignStudent(s1);
            s1.setAdvisor(a1);
            s1.viewCurrentSchedule();
            ArrayList<String> notee = new ArrayList<String>();
            notee.add("");
            s1.setNotes(notee);
            
            
            s1.getCurrentElectives();
            s1.getCompletedElectives();
            ArrayList<String> newnotes = s1.getNotes();
            newnotes.add("You have taken 2 elective stats classes but havent declared stats as your application area. I reccomment making stats your application area.");
            s1.setNotes(newnotes);
            System.out.println("\n\ns1 notes: " + s1.getNotes());

            

        }else if(input.equals("QUIT")){
            System.out.println("Goodbye!");
            System.exit(0);
        }else{
            //Logic to check if account exists already
        }
        //end of scenario 2 code
    }
    public static void testLoader(){
        DataLoader dataLoader = new DataLoader();

        ArrayList<Student> electives = DataLoader.getStudents();
        //System.out.println
        System.out.println(electives.get(0).getFirstName());
    }

    private static void checkProgress(Student braxWest) {
        System.out.println("Brax West's Completed Courses: ");
        for(Course course : braxWest.getCurrentCourses()) {
            System.out.println(course.getCourseSubject() + " " + course.getCourseNumber() + " Grade: " + braxWest.getCompletedCourses().get(course));
        }
        System.out.println("\nBrax West's Remaining Courses:");
        for (Course course : braxWest.getCurrentCourses()) {
            System.out.println(course.getCourseSubject() + " " + course.getCourseNumber());
        }
    }

    private static void generateSemesterPlan(Student braxWest) {
        try {
            FileWriter writer = new FileWriter("BraxWest_SemesterPlan.txt");
            writer.write("Brax West's 8-Semester Plan:\n\n");
            ArrayList<Course> coursesToTake = new ArrayList<>(braxWest.getCurrentCourses());
            for (int i = 1; i <= 8; i++) {
                writer.write("Semester " + i + ":\n");
                writer.write("Courses to Take:\n");
                for (Course course : coursesToTake) {
                    writer.write(course.getCourseSubject() + " " + course.getCourseNumber() + " - " + course.getCourseName() + "\n");
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("\n8-Semester Plan for Brax West has been generated and saved to BraxWest_SemesterPlan.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the 8-Semester Plan to a file.");
            e.printStackTrace();
        }
    }
}