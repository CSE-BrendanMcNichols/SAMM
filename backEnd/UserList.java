package backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private ArrayList<Student> studentList;
    private ArrayList<Advisor> advisorList;
    private ArrayList<Administrator> adminList;

    private static UserList userList = new UserList();

    private UserList() {
        studentList = new ArrayList<>();
        advisorList = new ArrayList<>();
        adminList = new ArrayList<>();
    }

    public static UserList getInstance() {
        return userList;
    }

    public boolean addUser(String firstName, String lastName, String userName, String email, String password, UserType userType) {
        if (getUser(userName) != null) {
            return false;
        }
        switch (userType) {
            case STUDENT:
                studentList.add(new Student(firstName, lastName, userName, email, UUID.randomUUID()));
                break;
            case ADVISOR:
                advisorList.add(new Advisor(firstName, lastName, userName, email, UUID.randomUUID()));
                break;
            case ADMINISTRATOR:
                adminList.add(new Administrator(firstName, lastName, userName, email, UUID.randomUUID()));
                break;
            default:
                return false;
        }
        return true;
    }

    public User getUser(String userName) {
        for (User user : studentList) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        for (User user : advisorList) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        for (User user : adminList) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    // do we need this method?
    public void editUser(User user) {
    
    }

    public void saveUsers() {
        DataWriter dataWriter = new DataWriter();
        // Note: Seperated as 3 different methods as saveUsers can not differentiate list if use saveUSers
        dataWriter.saveStudents(studentList);
        dataWriter.saveAdvisors(advisorList);
        dataWriter.saveAdministrators(adminList);
    }
}