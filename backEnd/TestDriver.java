package backEnd;

import java.util.ArrayList;

public class TestDriver {
    private SchedulingFacade facade;

    TestDriver(){
        //facade = new SchedulingFacade;
    }
    public void run(){
        scenario1();
        //scenario2();
    }
    public static void main(String[] args) {
        scenario1();
    }
    public static void scenario1(){
        Semester testsemester = Semester.SPRING;
        Course course0;
        Course course1;
        ArrayList<Requirement> prereq1;
        ArrayList<Requirement> coreq1;
        CourseState status1;
        ArrayList<String> testnote1;
        Year testyear1;
        Advisor testadvisor;
        Major testmajor;
        Course testcourse1 = new Course("course1", testsemester, 0, null, null, "desc", 1, 'C', 'C', null);
        /*Student teststudent = new Student("name", "pass", "email", "uuid", testyear1,
        testadvisor, testmajor, 3.14, 1,
        null, testcourse1, testnote1);
        */
        Student teststudent = new Student("name", "pass", "email", "uuid");
        teststudent.viewFutureSchedule();
    }
}