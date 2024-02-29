package backEnd;

import java.util.UUID;

public class Administrator extends User {

    private User user;

    public Administrator(String username, String password, String email, String uscid, UUID uuid) {
        super(username, password, email, uscid, uuid);
    }
    
    private void overrideStudentGrade(String name, char grade) {
        System.out.println("The new grade for " + name + "will be " + grade);
    }

    private void updateMajor(String majorDetails) {
        System.out.println("The new major is " + majorDetails);
    }

    private User searchUser(String name) {
        
    }

}
