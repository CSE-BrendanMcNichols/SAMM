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

public class StudentTester {
    
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
    
    @Test
    public void testStudentToPointGradeNormal(){
        Student student = new Student();
        double test = student.toPointGrade("B");
        assertEquals(3.0 , test);
    }

    @Test
    public void testStudentToPointGradeBPlus(){
        Student student = new Student();
        double test = student.toPointGrade("B+");
        assertEquals(3.3 , test);
    }
    
    @Test
    public void testStudentToPointGradeBMinus(){
        Student student = new Student();
        double test = student.toPointGrade("B-");
        assertEquals(2.7 , test);
    }

    @Test
    public void testStudentToPointGradeNotGrade(){
        Student student = new Student();
        double test = student.toPointGrade("test");
        assertEquals(0.0 , test);
    }

    @Test
    public void testStudentToPointGradeNull(){
        Student student = new Student();
        double test = student.toPointGrade(null);
        assertEquals(0.0 , test);
    }

    @Test
    public void testCalculateGPAAllAs(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse, "A");
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        assertEquals(4.0, student.getOverallGrade());
    }

    @Test
    public void testCalculateGPAOneBOneA(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse, "B");
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        assertEquals(3.5, student.getOverallGrade());
    }

    @Test
    public void testCalculateGPAInvalidGrades(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, "test");
        testCourses.put(testCourse, "test");
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        assertEquals(0.0, student.getOverallGrade());
    }

    @Test
    public void testCalculateGPANullGrades(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, "test");
        testCourses.put(testCourse, "test");
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        assertEquals(0.0, student.getOverallGrade());
    }
}
