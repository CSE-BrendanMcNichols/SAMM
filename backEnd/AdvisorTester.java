package backEnd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdvisorTester {
    
    /* 
    @BeforeClass
	public void oneTimeSetup() {
		
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		//runs before each test
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}
    */


    //testing assignedStudents
    @Test
    public void testAdvisorAssignStudentNormal(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        advisor.assignStudent(student);
        assertEquals(student , advisor.getAssignedStudents().get(0));
    }

    @Test
    public void testAdvisorAssignStudentNull(){ //not supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        advisor.assignStudent(null);
        assertEquals(student , advisor.getAssignedStudents().get(0));
    }

    @Test
    public void testAdvisorAssignTwoStudents(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        Student student2 = new Student();
        advisor.assignStudent(student);
        advisor.assignStudent(student2);
        assertEquals(student , advisor.getAssignedStudents().get(0));
        assertEquals(student2 , advisor.getAssignedStudents().get(1));
    }

    //tesing unassignedStudent
    @Test
    public void testAdvisorUnassignStudentNormal(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        ArrayList<Student> assignedStudents = new ArrayList<Student>();
        assignedStudents.add(student);
        advisor.setAssignedStudents(assignedStudents);
        advisor.unAssignStudent(student);
        assertEquals(0 , advisor.getAssignedStudents().size(), "Unassigned the Student: \" + student.getUsername() + \" from advisor\" + advisor.getUsername())");
    }

    @Test
    public void testAdvisorUnassignStudentEmpty(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        ArrayList<Student> assignedStudents = new ArrayList<Student>();
        advisor.setAssignedStudents(assignedStudents);
        advisor.unAssignStudent(student);
        assertEquals(0, advisor.getAssignedStudents().get(0), "There are no students assigned");
    }

    @Test
    public void testAdvisorUnassignFirstStudentOfTwo(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        Student student2 = new Student();
        ArrayList<Student> assignedStudents = new ArrayList<Student>();
        assignedStudents.add(student);
        assignedStudents.add(student2);
        advisor.setAssignedStudents(assignedStudents);
        advisor.unAssignStudent(student);
        assertEquals(student2 , advisor.getAssignedStudents().get(0), "Unassigned the Student: \" + student.getUsername() + \" from advisor\" + advisor.getUsername())");
    }

    @Test
    public void testAdvisorUnassignLastStudentOfThree(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        ArrayList<Student> assignedStudents = new ArrayList<Student>();
        assignedStudents.add(student);
        assignedStudents.add(student2);
        assignedStudents.add(student3);
        advisor.setAssignedStudents(assignedStudents);
        advisor.unAssignStudent(student3);
        assertEquals(2 , advisor.getAssignedStudents().size(), "Unassigned the Student: \" + student.getUsername() + \" from advisor\" + advisor.getUsername())");
    }
   
}
