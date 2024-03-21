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
    public void testStudentOverallGradeEmpty(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        assertEquals(0.0, student.getOverallGrade());
    }

    @Test
    public void testStudentOverallGradeOneGrade(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        assertEquals(4.0, student.getOverallGrade());
    }

    @Test
    public void testStudentOverallGradeTwoGrade(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse, "B");
        assertEquals(4.0, student.getOverallGrade());
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
        assertEquals(4.0, student.calculateGPA());
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
        assertEquals(3.5, student.calculateGPA());
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
        assertEquals(0.0, student.calculateGPA());
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
        assertEquals(0.0, student.calculateGPA());
    }

    @Test
    public void testGetCreditsAccumulatedBaseLine(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse, "A");
        student.setCompletedCourses(testCourses);
        assertEquals(9, student.getCreditsAccumulated());
    }

    @Test
    public void testGetCreditsAccumulatedEmpty(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        student.setCompletedCourses(testCourses);
        assertEquals(0, student.getCreditsAccumulated());
    }

    public void updateCourseCompleted(Course updateCourse, String courseGrade) {
        // System.out.println("updateCourseCompleted called. updateCourse: " +
        // updateCourse);
        for (Course course : this.currentCourses) {
            if (course.getUuid() == updateCourse.getUuid()) {
                currentCourses.remove(course);
                break;
            }
        }
        this.completedCourses.put(updateCourse, courseGrade);
        updateCredits();
        updateOverallGrade();
    }

    @Test
    public void testUpdateCourseCompletedBaseLineCourse(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCourses.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(course, "A")
        assertEquals(course, student.getCompletedCourses().keySet().iterator().next());
    }

    @Test
    public void testUpdateCourseCompletedBaseLineGrade(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCourses.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(course, "A")
        assertEquals("A", student.getCompletedCourses().Values().iterator().next());
    }

    @Test
    public void testUpdateCourseCompletedBaseLineRemoved(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCourses.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(course, "A")
        assertEquals(0, student.getCurrentCourses.size());
    }

    @Test
    public void testUpdateCourseCompletedNullCourse(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCourses.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(null, "A")
        assertEquals(null, student.getCompletedCourses().KeySet().iterator().next());
    }
    //Find way to check Hash Size
}
