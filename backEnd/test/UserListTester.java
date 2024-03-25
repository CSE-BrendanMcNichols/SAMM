package backEnd.test;

import org.junit.Before;
import org.junit.Test;

import backEnd.Administrator;
import backEnd.Advisor;
import backEnd.Major;
import backEnd.Student;
import backEnd.User;
import backEnd.UserList;
import backEnd.Year;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.Assert.*;

public class UserListTester {
    private UserList userList;

    @Before
    public void setUp() {
        userList = UserList.getInstance();

        Student studentBrax = new Student("Brax", "West", "12345", "brax@email.sc.edu", "braxwest", "pass123", 
            Year.Junior, null, new Major("CS"), 3.5, 27, 
            new HashMap<>(), new ArrayList<>(), new ArrayList<>(), 
            UUID.fromString("daa77ead-9fcb-47f0-964d-3cff541591cd"), new ArrayList<>(), new ArrayList<>(), 
            "Digital Design");

        Advisor advisorMary = new Advisor("Mary", "Ziemba", "HE12865", "MaryZiemba@email.sc.edu", "MaryZiemba", "P4zzWrd8888", 
            new ArrayList<>(), UUID.fromString("2c29e60d-c02e-450a-8ac1-0c65266cabac"), "CS Department");

        studentBrax.setAdvisor(advisorMary);
        advisorMary.assignStudent(studentBrax);

        Administrator adminJohnDoe = new Administrator("John", "Doe", "HE82021", "JohnDoe@email.sc.edu", "JohnDoe", "Password123", 
            UUID.fromString("886c33a5-a9c1-4314-8284-ec73d657a973"));

        userList.addUser(studentBrax);
        userList.addUser(advisorMary);
        userList.addUser(adminJohnDoe);
    }

    @Test
    public void testAddUserAndRetrieve() {
        UUID newStudentUUID = UUID.randomUUID();
        Student newStudent = new Student("New", "Student", "SE83332", "newstudent@email.sc.edu", "newstudent", "newpass", 
            Year.Freshman, null, new Major("CIS"), 3.0, 15, 
            new HashMap<>(), new ArrayList<>(), new ArrayList<>(), 
            newStudentUUID, new ArrayList<>(), new ArrayList<>(), 
            "General CIS");
        assertTrue(userList.addUser(newStudent));

        User retrievedUser = userList.getUserByUsername("newstudent");
        assertNotNull(retrievedUser);
        assertEquals(newStudentUUID, retrievedUser.getUuid());
        assertEquals("CIS", ((Student) retrievedUser).getMajor().getMajor());
    }

    @Test
    public void testAdvisorStudentRelationship() {
        Advisor retrievedAdvisor = (Advisor) userList.getUserByUsername("MaryZiemba");
        assertNotNull(retrievedAdvisor);
        assertFalse(retrievedAdvisor.getAssignedStudents().isEmpty());

        Student assignedStudent = retrievedAdvisor.getAssignedStudents().get(0);
        assertEquals("Brax", assignedStudent.getFirstName());
        assertTrue("CS".equals(assignedStudent.getMajor().getMajor()) || "CIS".equals(assignedStudent.getMajor().getMajor()));
    }
}
