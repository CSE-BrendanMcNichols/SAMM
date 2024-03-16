package backEnd;

import java.util.ArrayList;

/**
 * Manages a collection of users, including students, advisors, and administrators.
 * Provides functionality to add, update, and delete users, and to retrieve users by username or USC ID.
 */
public class UserList {
    private static UserList userList; // Singleton instance of UserList
    private ArrayList<User> users =new ArrayList<>(); // List to store all users

    private static ArrayList<Student>  students = new ArrayList<Student>();
    private static ArrayList<Advisor>  advisors = new ArrayList<Advisor>();
    private static ArrayList<Administrator>  administrators = new ArrayList<Administrator>();


    /**
     * Private constructor to prevent instantiation.
     * Initializes the list of users.
     */
    private UserList() {
        //users = new ArrayList<>();
        //loadUsers();
    }

    /**
     * Returns the singleton instance of UserList.
     * @return The singleton instance.
     */
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    
    public void initializeAdminstrators() {
        administrators = DataLoader.loadAdministrators();
        if (administrators != null)
            administrators.forEach(this::addUser);

    }

    public void initializeStudentsNoAdvisor() {
        students = DataLoader.loadStudentsNoAdvisor();
        if (students != null)
            students.forEach(this::addUser);

    }

    public void initializeAdvisors() {
        advisors = DataLoader.loadAdvisors();
        if (advisors != null)
            advisors.forEach(this::addUser);

    }

    /**
     * Loads users from the data source into the users list.
     */
    private void loadUsers() {
        
        administrators = DataLoader.loadAdministrators();
        //students = DataLoader.loadStudents();
        advisors = DataLoader.loadAdvisors();
        
        if (students != null) students.forEach(this::addUser);
        if (advisors != null) advisors.forEach(this::addUser);
        if (administrators != null) administrators.forEach(this::addUser);
    }

    /**
     * Retrieves a user by their username.
     * @param username The username of the user.
     * @return The user with the specified username, or null if not found.
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Retrieves a user by their USC ID.
     * @param uscid The USC ID of the user.
     * @return The user with the specified USC ID, or null if not found.
     */
    public User getUserByUscId(String uscid) {
        for (User user : users) {
            if (user.getUscid().equals(uscid)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a new user to the list if they don't already exist.
     * @param user The user to add.
     * @return true if the user was added, false if the user already exists.
     */
    public boolean addUser(User user) {
        if (getUserByUsername(user.getUsername()) != null || getUserByUscId(user.getUscid()) != null) {
            System.out.println("User already exists.");
            return false;
        }
        users.add(user);
        return true;
    }

    /**
     * Deletes a user by their username.
     * @param username The username of the user to delete.
     * @return true if the user was deleted, false otherwise.
     */
    public boolean deleteUserByUsername(String username) {
        return users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
    }

    /**
     * Deletes a user by their USC ID.
     * @param uscid The USC ID of the user to delete.
     * @return true if the user was deleted, false otherwise.
     */
    public boolean deleteUserByUscId(String uscid) {
        return users.removeIf(user -> user.getUscid().equals(uscid));
    }

    /**
     * Updates the details of an existing user.
     * @param updatedUser The user with updated details.
     * @return true if the user was updated, false if the user was not found.
     */
    public boolean updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUuid().equals(updatedUser.getUuid())) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public  ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    

    
}
