package backEnd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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

    public void checkHours(HashMap<Course, String> completedCourses) {
        int creditTotal = 0;
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            creditTotal += course.getCourseHours();
        }
        System.out.println("credit hours completed are: " + creditTotal);
    }

    @Test
    public void testCheckHoursBaseLine(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        Course testCourse3 = new Course();
        testCourses.put(testCourse, "A");
        testCourses.put(testCourse2, "B");
        testCourses.put(testCourse3, "C");
        student.setCompletedCourses(testCourses);
        student.checkHours(student.getCompletedCourses());
        assertEquals("credit hours completed are: 9"+
                    "\n", outputStreamCaptor.toString());
    }

    @Test
    public void testCheckHoursOneCourse(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        Course testCourse = new Course();
        testCourses.put(testCourse, "A");
        student.setCompletedCourses(testCourses);
        student.checkHours(student.getCompletedCourses());
        assertEquals("credit hours completed are: 3"+
                    "\n", outputStreamCaptor.toString());
    }

    @Test
    public void testCheckHoursNoCourses(){
        Student student = new Student();
        HashMap<Course, String> testCourses = new HashMap<Course, String>();
        student.setCompletedCourses(testCourses);
        student.checkHours(student.getCompletedCourses());
        assertEquals("credit hours completed are: 0"+
                    "\n", outputStreamCaptor.toString());
    }

    @Test
    public void testUpdateCurrentCoursesBaseLine(){
        Student student = new Student();
        Course testCourse = new Course();
        student.updateCurrentCourses(testCourse);
        assertEquals(testCourse, student.getCurrentCourses().get(0));
    }

    @Test
    public void testUpdateCurrentCoursesMultipleCourses(){
        Student student = new Student();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        Course testCourse3 = new Course();
        student.updateCurrentCourses(testCourse);
        student.updateCurrentCourses(testCourse2);
        student.updateCurrentCourses(testCourse3);
        assertEquals(testCourse3, student.getCurrentCourses().get(2));
    }
    //Should not allow same Course Twice
    @Test
    public void testUpdateCurrentCoursesDuplicateCourse(){
        Student student = new Student();
        Course testCourse = new Course();
        student.updateCurrentCourses(testCourse);
        student.updateCurrentCourses(testCourse);
        assertEquals(1, student.getCurrentCourses().size());
    }

    @Test
    public void testUpdateCurrentCoursesNull(){
        Student student = new Student();
        student.updateCurrentCourses(null);
        assertEquals(0, student.getCurrentCourses().size());
    }

    @Test
    public void testAddElectiveBaseLine(){
        Student student = new Student();
        Elective elective = new Elective();
        student.addElective(elective);
        assertEquals(elective, student.getCurrentElectives().get(0));
    }

    @Test
    public void testAddElectiveNull(){
        Student student = new Student();
        student.addElective(null);
        assertEquals(0, student.getCurrentElectives().size());
    }

    @Test
    public void testAddElectiveMultiple(){
        Student student = new Student();
        Elective elective = new Elective();
        Elective elective2 = new Elective();
        student.addElective(elective);
        student.addElective(elective2);
        assertEquals(elective2, student.getCurrentElectives().get(1));
    }

    @Test
    public void testAddElectiveDuplicate(){
        Student student = new Student();
        Elective elective = new Elective();
        student.addElective(elective);
        student.addElective(elective);
        assertEquals(1, student.getCurrentElectives().size());
    }

    @Test
    public void testRemoveElectiveBaseLine(){
        Student student = new Student();
        Elective elective = new Elective();
        student.addElective(elective);
        student.removeElective(elective);
        assertEquals(0, student.getCurrentElectives().size());
    }

    @Test
    public void testRemoveElectiveEmptyElectives(){
        Student student = new Student();
        Elective elective = new Elective();
        student.removeElective(elective);
        assertEquals(0, student.getCurrentElectives().size());
    }

    @Test
    public void testRemoveElectiveNull(){
        Student student = new Student();
        Elective elective = new Elective();
        student.addElective(elective);
        student.removeElective(null);
        assertEquals(1, student.getCurrentElectives().size());
    }

    @Test
    public void testAddElectiveRemove2(){
        Student student = new Student();
        Elective elective = new Elective();
        Elective elective2 = new Elective();
        student.addElective(elective);
        student.addElective(elective2);
        student.removeElective(elective2);
        assertEquals(elective, student.getCurrentElectives().get(0));
    }

    public void updateElectiveCompleted(Elective elect) {

        removeElective(elect);
        if (this.completedElectives != null) {
            this.completedElectives = new ArrayList<Elective>();
            this.completedElectives.add(elect);
            // System.out.println("Elective: " + elect.getName() + " is added tp completed
            // electives list");
        }
    }

    public void addNotes(String note) {
        if (notes == null) {
            this.notes = new ArrayList<String>();
        }
        notes.add(note);
    }

    public String displayStudent() {
        return "Student:: " + this.getUscid() + " : " + this.getFirstName() + " " + this.getLastName();
    }

    public static void checkProgress(User user) {

        if(user.getType() != UserType.STUDENT){
            return;
        }

        Student student = UserList.getInstance().getStudent(user.getUuid());

        System.out.println("\n" + student.getFirstName() + " " + student.getLastName() + "'s Completed Courses: ");
        System.out.println("------------------------------------");
        for(Course course : student.getCurrentCourses()) {
            System.out.println(course.getCourseName() + " " + course.getCourseNumber() + " Grade: " + student.getCompletedCourses().get(course));
        }
        System.out.println("\n"+ student.getFirstName() + " " + student.getLastName() +"'s Remaining Courses:");
        System.out.println("------------------------------------");
        for (Course course : student.getCurrentCourses()) {
            System.out.println(course.getCourseName() + " " + course.getCourseNumber());
        }
    }

    public static void generateSemesterPlan(User user) {
        try {

            if(user.getType() != UserType.STUDENT){
                return;
            }
            Student student = UserList.getInstance().getStudent(user.getUuid());
            FileWriter writer = new FileWriter("backEnd/" + student.getFirstName() + student.getLastName() + "_SemesterPlan.txt");
            writer.write(student.getFirstName() + " " + student.getLastName() + "'s 8-Semester Plan:\n\n");
            ArrayList<Course> coursesToTake = new ArrayList<>(student.getCurrentCourses());
            for (int i = 1; i <= 8; i++) {
                writer.write("Semester " + i + ":\n");
                writer.write("----------\n");
                writer.write("Courses to Take:\n");
                // TODO: fix the logic
                for (Course course : coursesToTake) {
                    writer.write(course.getCourseSubject() + " " + course.getCourseNumber() + " - " + course.getCourseName() + "\n");
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("\n8-Semester Plan for " + student.getFirstName() + " " + student.getLastName() + "has been generated and saved to " + 
            student.getFirstName() + student.getLastName() + "SemesterPlan.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the 8-Semester Plan to a file.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Student [gradeYear=" + gradeYear + ", advisor=" + advisor + ", major=" + major
                + ", overallGrade="
                + overallGrade + ", credits=" + credits + ", completedCourses=" + completedCourses + ", currentCourses="
                + currentCourses + ", notes=" + notes + ", applicationArea=" + applicationArea + ", currentElectives="
                + currentElectives + ", completedElectives=" + completedElectives + ", uuid=" + uuid + "]";
    }
}   
