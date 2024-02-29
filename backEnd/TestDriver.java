package backEnd;

public class TestDriver {
    private SchedulingFacade facade;
    private User user;
    private Advisor advisor;
    private Student student;
    private Administrator administrator;
    private Course course;
    private MajorList majorList;
    private UserList userList;
    private CourseList courseList;
    private DataLoader dataLoader;
    private DataWriter dataWriter;

    TestDriver(){
        SchedulingFacade facade = new SchedulingFacade(user, advisor, student, 
        administrator, course, majorList, 
        userList, courseList);
    }

    public void run(){
        scenario1();
        //scenario2();
    }

    public void scenario1() {
        facade.accessUserActions();
    }
}