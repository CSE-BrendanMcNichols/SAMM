package backEnd;

import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationFacade {
    private UserList userList = UserList.getInstance();
    private static ApplicationFacade applicationFacade;

    private ApplicationFacade() {
        Cache.getInstance().initializeCourses();
        Cache.getInstance().initializeRequirements();
        //initialize with Course Requirements
        Cache.getInstance().initializeCourseRequirements();

        Cache.getInstance().initializeApplicationAreas();
        /*
        Cache.getInstance().initializeElectives();
        Cache.getInstance().initializeMajors();
        
        Cache.getInstance().initializeAdminstrators();
        Cache.getInstance().initializeStudentsNoAdvisor();
        Cache.getInstance().initializeAdvisors();
        // initialize with Advisor
        Cache.getInstance().initializeStudentsAdvisor();
        */
        
    }

    public static ApplicationFacade getInstance() {
        if (applicationFacade == null) {
            applicationFacade = new ApplicationFacade();
        }
        return applicationFacade;
    }

    public boolean registerUser(UserType type, String firstName, String lastName, String uscid, String email,
            String username, String password) {
        
        System.out.println("type:" + type);

        switch (type) {
            case STUDENT:
                Student student = new Student(firstName, lastName, uscid, email,  username, password, 
                Year.Freshman, null, null, 0, 0, null, null, null,null,null,"");

                userList.addStudent(student);
                DataWriter.saveStudents(userList.getStudents());
                break;
            case ADVISOR:
                Advisor advisor = new Advisor(firstName, lastName, uscid, email, username, password);
                userList.addAdvisor(advisor);
                DataWriter.saveAdvisors(userList.getAdvisors());
                break;
            case ADMINISTRATOR:
                Administrator administrator = new Administrator(firstName, lastName, uscid, email, username, password);
                userList.addAdministrator(administrator);
                DataWriter.saveAdministrators(userList.getAdministrators());
                break;
            default:
                return false;
        }
        // userList.loadUsers();
        return true;
    }

    /**
     * Attempts to log in a user with the provided username and password.
     * 
     * @param username The username of the user attempting to log in.
     * @param password The password of the user.
     * @return The User object if login is successful; null otherwise.
     */
    public User loginUser(String username, String password) {
        User user = userList.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void accessUserActions() {
        // make a boolean that is quit and in the logout case you set quit to true;
        // The printing should be all in the UI and the
        boolean exit = false;
        System.out.println("These are your user Actions");
        while (!exit) {
            if (user.getRole() == "Student") {
                // swtich cases with all student actions
            } else if (user.getRole() == "Advisor") {
                // swtich case with all advisor actions
            } else if (user.getRole() == "Administrator") {
                // swtich case with all administrator actions

            }
        }
    }
}