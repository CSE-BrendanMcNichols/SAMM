package backEnd;

public class ApplicationFacade {
    private User user;
    private Advisor advisor;
    private Student student;
    private Administrator administrator;
    private Course course;
    private DataLoader dataLoader;
    private DataWriter dataWriter;

    // Empty constructor. No need to pass individual entity instances
    public ApplicationFacade() {}

    public User login(String username, String password) {
        User user = UserList.getInstance().getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean registerUser(String firstName, String lastName, String uscid, String email, String username, String password, UserType type) {
        if (UserList.getInstance().getUserByUsername(username) != null || UserList.getInstance().getUserByUscid(uscid) != null) {
            return false;
        }

        User newUser = new User(firstName, lastName, uscid, email, username, password, type);
        return UserList.getInstance().addUser(newUser);
    }
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