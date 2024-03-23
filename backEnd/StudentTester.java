package backEnd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StudentTester {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeClass
	public void oneTimeSetup() {
		
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}
    
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
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        assertEquals(4.0, student.getOverallGrade());
    }

    @Test
    public void testStudentOverallGradeTwoGrade(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse2, "B");
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        assertEquals(4.0, student.getOverallGrade());
    }

    @Test
    public void testStudentOverallGradeInvalidGrade(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse2, "G");
        student.setCompletedCourses(testCourses);
        student.updateOverallGrade();
        assertEquals(2.0, student.getOverallGrade());
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
        Course testCourse2 = new Course();
        Course testCourse3 = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse2, "A");
        testCourses.put(testCourse3, "A");
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

    @Test
    public void testUpdateCourseCompletedBaseLineCourse(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCoursesArray.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(testCourse, "A");
        assertEquals(testCourse, student.getCompletedCourses().keySet().iterator().next());
    }

    @Test
    public void testUpdateCourseCompletedBaseLineGrade(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCoursesArray.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(testCourse, "A");
        testCourses = student.getCompletedCourses();
        String testString = testCourses.values().iterator().next();
        assertEquals("A", testString);
    }

    @Test
    public void testUpdateCourseCompletedNotCurrentClassGrade(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        student.updateCourseCompleted(testCourse, "A");
        testCourses = student.getCompletedCourses();
        String testString = testCourses.values().iterator().next();
        assertEquals("A", testString);
    }

    @Test
    public void testUpdateCourseCompletedBaseLineRemoved(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCoursesArray.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(testCourse, "A");
        assertEquals(0, student.getCurrentCourses().size());
    }

    @Test
    public void testUpdateCourseCompletedBaseLineGradeChanged(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCoursesArray.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(testCourse, "A");
        assertEquals(4.0, student.getOverallGrade());
    }

    @Test
    public void testUpdateCourseCompletedBaseLineCreditsChanged(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCoursesArray.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(testCourse, "A");
        assertEquals(3, student.getCredits());
    }

    @Test
    public void testUpdateCourseCompletedNullCourse(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        ArrayList<Course> testCoursesArray = new ArrayList<Course>();
        testCoursesArray.add(testCourse);
        student.setCurrentCourses(testCoursesArray);
        student.updateCourseCompleted(null, "A");
        assertEquals(0, student.getCompletedCourses().size(), "Error null is not a course");
    }

    //These Tests don't seem to properly catch the errors so manually check if they succeed or fail
    @Test
    public void testViewClassGradesBaseLine(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        Course testCourse3 = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse2, "B");
        testCourses.put(testCourse3, "C");
        student.setCompletedCourses(testCourses);
        student.viewClassGrades();
        assertEquals("These are your class grades:"+
                    "\nTEST, A"+
                    "\nTEST, B"+
                    "\nTEST, C"+
                    "\n", outputStreamCaptor.toString());
    }
    //These Tests don't seem to properly catch the errors so manually check if they succeed or fail
    @Test
    public void testViewClassGradesOneClass(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        student.setCompletedCourses(testCourses);
        student.viewClassGrades();
        assertEquals("These are your class grades:"+
                     "\nTEST, A"+
                     "\n", outputStreamCaptor.toString());
    }
    @Test
    public void testViewClassGradesOneClassTEST(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        student.setCompletedCourses(testCourses);
        student.viewClassGrades();
        assertEquals("These are your class grades:"+
                     "\nTEST, A"+
                     "\n", outputStreamCaptor.toString());
    }
    //These Tests don't seem to properly catch the errors so manually check if they succeed or fail
    @Test
    public void testViewClassGradesNoClassError(){
        Student student = new Student();
        student.viewClassGrades();
        assertEquals("Error No Classes", outputStreamCaptor.toString());
    }
    //These Tests don't seem to properly catch the errors so manually check if they succeed or fail
    @Test
    public void testViewClassGradesNullCourseSubject(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourse.setCourseSubject(null);
        testCourses.put(testCourse, "A");
        student.setCompletedCourses(testCourses);
        student.viewClassGrades();
        assertEquals("These are your class grades:"+
                     "\nError Course Subject is Null, A"+
                     "\n", outputStreamCaptor.toString());
    }
    //These Tests don't seem to properly catch the errors so manually check if they succeed or fail
    @Test
    public void testViewClassGradesNullGrade(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, null);
        student.setCompletedCourses(testCourses);
        student.viewClassGrades();
        assertEquals("These are your class grades:"+
                     "\nTEST, Error null is not a Grade"+
                     "\n", outputStreamCaptor.toString());
    }

    @Test
    public void testRiskOfFailureSuccess(){
        Student student = new Student();
        student.setOverallGrade(0);
        assertEquals(true, student.riskOfFailure());
    }
    @Test
    public void testRiskOfFailureFailure(){
        Student student = new Student();
        student.setOverallGrade(4.0);
        assertEquals(false, student.riskOfFailure());
    }
    @Test
    public void testRiskOfFailureNegativeNumber(){
        Student student = new Student();
        student.setOverallGrade(-4.0);
        assertEquals(true, student.riskOfFailure());
    }
    @Test
    public void testRiskOfFailureExactly25(){
        Student student = new Student();
        student.setOverallGrade(2.5);
        assertEquals(false, student.riskOfFailure());
    }

    public void viewCurrentSchedule() {
        System.out.println("--------------------------");
        System.out.println("Current Courses:");
        for (Course course : this.currentCourses) {
            System.out.println(course.displayCourse());
        }
        System.out.println("Current Electives");
        for (Elective elective : this.currentElectives) {
            System.out.println(elective.getName());
            if (elective.getCourses() != null) {
                for (Course course : elective.getCourses()) {
                    System.out.println(course.displayCourse());
                }
            }
        }
        System.out.println("--------------------------");

        // System.out.println("End of Current Schedule");
    }
    @Test
    public void testViewCurrentScheduleOnlyOneClass(){
        Student student = new Student();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        ArrayList<Course> testCourses = new ArrayList<Course>();
        testCourses.add(testCourse);
        student.setCurrentCourses(testCourses);
        student.viewCurrentSchedule();
        assertEquals("--------------------------"+
                     "\nCurrent Courses:"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\nCurrent Electives"+
                     "\n--------------------------"+
                     "\n", outputStreamCaptor.toString());
    }

    @Test
    public void testViewCurrentScheduleBaseLine(){
        Student student = new Student();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        ArrayList<Course> testCourses = new ArrayList<Course>();
        testCourses.add(testCourse);
        testCourses.add(testCourse2);
        student.setCurrentCourses(testCourses);
        Elective testElective = new Elective(testCourses);
        ArrayList<Elective> testElectives = new ArrayList<Elective>();
        testElectives.add(testElective);
        student.setCurrentElectives(testElectives);
        student.viewCurrentSchedule();
        assertEquals("--------------------------"+
                     "\nCurrent Courses:"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\nCurrent Electives"+
                     "\ndefault name"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\n--------------------------"+
                     "\n", outputStreamCaptor.toString());
    }
    @Test
    public void testViewCurrentScheduleNullElective(){
        Student student = new Student();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        ArrayList<Course> testCourses = new ArrayList<Course>();
        testCourses.add(testCourse);
        testCourses.add(testCourse2);
        student.setCurrentCourses(testCourses);
        Elective testElective = null;
        ArrayList<Elective> testElectives = new ArrayList<Elective>();
        testElectives.add(testElective);
        student.setCurrentElectives(testElectives);
        student.viewCurrentSchedule();
        assertEquals("--------------------------"+
                     "\nCurrent Courses:"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\nCurrent Electives"+
                     "\nError Null Elective"+
                     "\n--------------------------"+
                     "\n", outputStreamCaptor.toString());
    }
    @Test
    public void testViewCurrentScheduleNoCourses(){
        Student student = new Student();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        ArrayList<Course> testCourses = new ArrayList<Course>();
        testCourses.add(testCourse);
        testCourses.add(testCourse2);
        Elective testElective = new Elective(testCourses);
        ArrayList<Elective> testElectives = new ArrayList<Elective>();
        testElectives.add(testElective);
        student.setCurrentElectives(testElectives);
        student.viewCurrentSchedule();
        assertEquals("--------------------------"+
                     "\nCurrent Courses:"+
                     "\nCurrent Electives"+
                     "\ndefault name"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\n--------------------------"+
                     "\n", outputStreamCaptor.toString());
    }
    @Test
    public void testViewCurrentScheduleNullCourse(){
        Student student = new Student();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        ArrayList<Course> testCourses = new ArrayList<Course>();
        testCourses.add(testCourse);
        testCourses.add(null);
        student.setCurrentCourses(testCourses);
        Elective testElective = new Elective(testCourses);
        ArrayList<Elective> testElectives = new ArrayList<Elective>();
        testElectives.add(testElective);
        student.setCurrentElectives(testElectives);
        student.viewCurrentSchedule();
        assertEquals("--------------------------"+
                     "\nCurrent Courses:"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\nError Null Class"+
                     "\n"+
                     "\nCurrent Electives"+
                     "\ndefault name"+
                     "\nError Null Class"+
                     "\n"+
                     "\nCourse Number:5555 Course Name:EMPTY CLASS Subject: TEST"+
                     "\n"+
                     "\n--------------------------"+
                     "\n", outputStreamCaptor.toString());
    }

    
}
