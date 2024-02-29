package backEnd;

import java.util.ArrayList;

public class TestDriver {
    private SchedulingFacade facade;

    TestDriver(){
        facade = new SchedulingFacade;
    }

    public void run(){
        scenario1();
        //scenario2();
    }

    public void scenario1(){
        Course testcourse1 = new Course("null", "null", 0, "hi", "null", null, 0, 0, 0, null);
        Student teststudent = new Student("name", "pass", "email", "uuid", "SENIOR",
        "Advisor advisor", "Major major", 3.14, 1,
        ArrayList<Course> completedCourses, ArrayList<Course> currentCourses, ArrayList<String> notes);
        teststudent.viewFutureSchedule();
    }
}