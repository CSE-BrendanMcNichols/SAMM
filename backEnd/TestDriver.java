package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        //scenario2()
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourse.setCourseSubject(null);
        testCourses.put(testCourse, "A");
        student.setCompletedCourses(testCourses);
        student.viewClassGrades();
    }
    public static void scenario1(){
        //Start of scenario 1 code
        Student braxWest = new Student("Brax", "West", "12345", "brax@email.sc.edu", "brax_west", "password", 
        UserType.STUDENT, Year.Junior, null, null, 0.0, 0, null, null, null);
        
        ArrayList <Semester> semesters = new ArrayList <Semester>();
        semesters.add(Semester.Spring);

        Course cs101 = new Course("CS101", "3", semesters, "CS101 desc", 3, 'C', CourseState.NOT_STARTED, UUID.randomUUID());
        Course cs202 = new Course("CS202", "3", semesters, "CS202 desc", 3, 'C', CourseState.NOT_STARTED, UUID.randomUUID());
        Course cs303 = new Course("CS303", "3", semesters, "CS303 desc", 3, 'C', CourseState.NOT_STARTED, UUID.randomUUID());

        HashMap<Course, String> completedCourses = new HashMap<>();
        completedCourses.put(cs101, "Passed");
        completedCourses.put(cs202, "Passed");
        completedCourses.put(cs303, "Passed");

        braxWest.setCompletedCourses(completedCourses);

        /* 
        ArrayList<Course> remainingCourses = new ArrayList<>();
        remainingCourses.add(new Course("GFL", "3", semesters, "Foreign Language", 3 , 'A', CourseState.NOT_STARTED, UUID.randomUUID()));
        braxWest.setRemainingCourses(remainingCourses);

        ArrayList<Elective> selectedElective = new ArrayList<>();
        selectedElective.add(new Elective(remainingCourses, "Spanish101", 3, UUID.randomUUID()));
        braxWest.setCurrentElectives(selectedElective);

        braxWest.setApplicationArea("Digital Design");
        */

        generateSemesterPlan(braxWest);

        //End of scenario 1 code
    }
    public static void scenario2(){
        //scenario 2 code
        ArrayList <Semester> semesters = new ArrayList <Semester>();
        semesters.add(Semester.Spring);

        Course c1 = new Course("CS101", "3", semesters, "CS101 desc", 3, 'C', CourseState.NOT_STARTED, UUID.randomUUID());
        Course c2 = new Course("CS202", "3", semesters, "CS202 desc", 3, 'C', CourseState.NOT_STARTED, UUID.randomUUID());
        Course c3 = new Course("CS303", "3", semesters, "CS303 desc", 3, 'C', CourseState.NOT_STARTED, UUID.randomUUID());

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
            
            Advisor a1 = new Advisor("AdvisorfirstName", "AdvisorlastName","1234","test@gmail.com",newuserusername,newuserpassword,"CIS");
            //a1.setUsername(newuserusername);
            //a1.setPassword(newuserpassword);
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

        ArrayList<Student> electives = DataLoader.loadStudentsNoAdvisor();
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

    //TODO: Implement method to select GFL elective and choose Digital Design

    /*
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
    

    public static void generateSemesterPlan(Student braxWest) {
        try {
            FileWriter writer = new FileWriter("backEnd/BraxWest_SemesterPlan.txt");
            writer.write("Brax West's 8-Semester Plan:\n\n");

            // Assuming the student needs to take CS courses in each semester
            ArrayList<Course> csCoursesToTake = generateCsCoursesToTake(braxWest);
            
            for (int i = 1; i <= 8; i++) {
                writer.write("Semester " + i + ":\n");
                writer.write("Courses to Take:\n");
                for (Course course : csCoursesToTake) {
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

    // Sample logic to generate CS courses to take
    public static ArrayList<Course> generateCsCoursesToTake(Student student) {
        ArrayList<Course> coursesToTake = new ArrayList<>();
        // Sample: Adding CS courses that the student hasn't completed yet
        for (Course course : student.getReq().getRequiredCourses()) {
            if (!student.getCompletedCourses().containsKey(course)) {
                coursesToTake.add(course);
            }
        }
        return coursesToTake;
    }
    */

    public static void generateSemesterPlan(Student braxWest) {
        try {
            FileWriter writer = new FileWriter("backEnd/BraxWest_SemesterPlan.txt");
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