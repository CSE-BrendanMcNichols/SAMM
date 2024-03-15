package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
        
        DataLoader dataLoader = new DataLoader();

        ArrayList<Major> electives = DataLoader.getMajors();

        electives.get(0).displaycoreReq();
    }
}