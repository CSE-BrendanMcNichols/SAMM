package backEnd;

import java.util.ArrayList;

public class UserList {
    private ArrayList<Student> studentList;
    private ArrayList<Advisor> advisorList;
    private ArrayList<Administrator> administratorList;
    private static UserList userList;

    private UserList() {
        studentList = new ArrayList<>();
        advisorList = new ArrayList<>();
        administratorList = new ArrayList<>();
    }

    public static UserList getInstance(){
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
}

    public boolean addUser(String firstName, String lastName, String userName, String email, String password, String userType) {
        if (getUser(userName) != null) {
        return false;
    }
    User newUser;
    if (userType.equalsIgnoreCase("student")) {
        newUser = new Student(firstName, lastName, userName, email, password);
        userList.getStudents().add((Student) newUser);
    } else if (userType.equalsIgnoreCase("advisor")) {
        newUser = new Advisor(firstName, lastName, userName, email, password);
        userList.getAdvisors().add((Advisor) newUser);
    } else if (userType.equalsIgnoreCase("administrator")) {
        newUser = new Administrator(firstName, lastName, userName, email, password);
        userList.getAdministrators().add((Administrator) newUser);
    } else {
        return false;
    }

    return true; 
}

    public User getUser(String userName) {
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    // editUsers method here. do we still need this?

    public void saveUsers() {
        dataWriter.saveStudents(studentList);
        dataWriter.saveAdvisors(advisorList);
        dataWriter.saveAdministrators(administratorList);
    }
}