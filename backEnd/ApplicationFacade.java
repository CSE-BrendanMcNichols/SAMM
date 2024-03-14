package backEnd;

public class ApplicationFacade {
    private UserList userList;

    public ApplicationFacade() {
        this.userList = UserList.getInstance();
    }

    /**
     * Attempts to register a new user with the given details.
     * 
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param uscid The USC ID of the user.
     * @param email The email address of the user.
     * @param username The username for the new account.
     * @param password The password for the new account.
     * @param type The type of the user (e.g., STUDENT, ADVISOR).
     * @return true if the registration was successful, false otherwise.
     */
    public boolean registerUser(String firstName, String lastName, String uscid, String email, String username, String password, UserType type) {
        if (userList.getUserByUsername(username) != null || userList.getUserByUscId(uscid) != null) {
            return false;
        }
        User newUser = new User(firstName, lastName, uscid, email, username, password, type);
        return userList.addUser(newUser);
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