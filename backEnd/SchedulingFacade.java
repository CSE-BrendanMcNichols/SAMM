package backEnd;

public class SchedulingFacade {
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

    public SchedulingFacade(User user, Advisor advisor, Student student, 
    Administrator administrator, Course course, MajorList majorList, 
    UserList userList, CourseList courseList) {
        this.user = user;
        this.advisor = advisor;
        this.student = student;
        this.administrator = administrator;
        this.course = course;
        this.majorList = majorList;
        this.userList = userList;
        this.courseList = courseList;
    }

    public void login () {
        user.login();    
    }

    public void loadData () {
        dataLoader.getUsers();
        dataLoader.getCourses();
        dataLoader.getMajors();
    }

    public void saveData() {
        dataWriter.saveUsers();
        dataWriter.saveCourses();
        dataWriter.saveMajors();
    }

    public void accessUserActions() {
        //make a boolean that is quit and in the logout case you set quit to true;
        boolean exit = false;
        System.out.println("These are your user Actions");
        while (!exit) {
            if(user.getRole() == "Student"){
                //swtich cases with all student actions
            }
            else if(user.getRole() == "Advisor") {
                //swtich case with all advisor actions
            }
            else if(user.getRole() == "Administrator") {
                //swtich case with all administrator actions
            }
        }
    }
}