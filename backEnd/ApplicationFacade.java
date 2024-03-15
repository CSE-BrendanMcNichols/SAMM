package backEnd;

import java.util.ArrayList;

public class ApplicationFacade {
    private UserList userList = UserList.getInstance();
    private static ApplicationFacade applicationFacade;

    public static ApplicationFacade getInstance() {
        if(applicationFacade == null) {
            applicationFacade = new ApplicationFacade();
            // Bootstrap the application by loading all the data from database.
            ApplicationBootstrap.initialize();
        }
        return applicationFacade;
    }    

    public boolean registerUser(UserType type, String firstName, String lastName, String uscid, String email, String username, String password) {
        switch (type) {
            case STUDENT:
                Student student = new Student(firstName, lastName, uscid, email, username, password);
                userList.addUser(student);

                // Please see the commented code. istead of loading the students every time a registerUSer method is called
                // do it once in bootstrap class and use it everywhere
                
                //ArrayList<Student> students = DataLoader.getStudents();
                //if (students == null) students = new ArrayList<>();
                //students.add(student);
                //DataWriter.saveStudents(students);
                UserList.getInstance().addUser(student);
                DataWriter.saveStudents(UserList.getStudents());
                break;
            case ADVISOR:
                Advisor advisor = new Advisor(firstName, lastName, uscid, email, username, password);
                userList.addUser(advisor);
                ArrayList<Advisor> advisors = DataLoader.getAdvisors();
                if (advisors == null) advisors = new ArrayList<>();
                advisors.add(advisor);
                DataWriter.saveAdvisors(advisors);
                break;
            case ADMINISTRATOR:
                Administrator administrator = new Administrator(firstName, lastName, uscid, email, username, password);
                userList.addUser(administrator);
                ArrayList<Administrator> administrators = DataLoader.getAdministrators();
                if (administrators == null) administrators = new ArrayList<>();
                administrators.add(administrator);
                DataWriter.saveAdministrators(administrators);
                break;
            default:
                return false;
        } 
        //userList.loadUsers();
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
        //make a boolean that is quit and in the logout case you set quit to true;
        //The printing should be all in the UI and the 
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