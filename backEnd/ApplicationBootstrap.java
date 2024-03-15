package backEnd;


public class ApplicationBootstrap {

    public static void initialize() {
        //initialize the data from database (json file)
        UserList userList = UserList.getInstance();
        MajorList majorList = MajorList.getInstance();
        CourseList courseList = CourseList.getInstance();
    }
   
}