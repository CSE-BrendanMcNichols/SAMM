package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
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
        //scenario2();
    }
    public static void main(String[] args) {
        //scenario1();
        // Arrange
        Course c1 = new Course("CS101", 3, "Spring 2023", "TTh 10:30-11:45", "Instructor 1");
        Course c2 = new Course("CS202", 3, "Spring 2023", "MWF 11:00-11:50", "Instructor 2");
        Course c3 = new Course("CS303", 3, "Fall 2022", "MWF 12:00-12:50", "Instructor 3");
        Major m1 = new Major("Computer Science");
        Student s1 = new Student("s1", "p1", "e1", "u1", Year.Freshman, null, m1, 3.5, 12, new HashMap<>(), new ArrayList<>(), new ArrayList<>(), UUID.randomUUID());
        s1.currentClasses.add(c1);
        s1.currentClasses.add(c2);
        s1.completedClasses.put(c1, "A");
        s1.completedClasses.put(c2, "B");
        //make current and completed classes public
        // Act
        Major futureMajor = s1.viewFutureSchedule();

        
    }
    public static void scenario1(){
        /*
        DataLoader dataLoader = new DataLoader();

        ArrayList<Major> electives = DataLoader.getMajors();

        electives.get(0).displaycoreReq();
        */

        Student braxWest = new Student("Brax", "West", "12345", "brax@email.sc.edu", "brax_west", "password", 
        UserType.STUDENT, Year.Junior, null, null, 0.0, 0, null, null, null);
        
        checkProgress(braxWest);
        generateSemesterPlan(braxWest);
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