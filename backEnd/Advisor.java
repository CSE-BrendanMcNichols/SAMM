package backEnd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This is a Advisor class
 * 
 * @author Sree
 */
public class Advisor extends User {
    
    private ArrayList<Student> assignedStudents;

    public Advisor(String username, String password, String email, String uscid, UUID uuid) {
        super(username, password, email, uscid, uuid);
    }

    public Advisor(String username, String password, String email, String uscid, UUID uuid, ArrayList<Student> assignedStudents) {
        this.assignedStudents = assignedStudents;
        super(username, password, email, uscid, uuid);
    }
    // TODO - implement the rest of the methods from the UML diagram

    public ArrayList<Student> getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(ArrayList<Student> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }


    
}
