package backEnd.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backEnd.Course;
import backEnd.CourseList;
import backEnd.CourseState;
import backEnd.DataWriter;
import backEnd.Semester;

public class CourseListTester {

    private CourseList courseList;
    private ArrayList<Course> courses;
    UUID uuid1 = UUID.randomUUID();
    UUID uuid2 = UUID.randomUUID();
    UUID uuid3 = UUID.randomUUID();
    UUID uuid4 = UUID.randomUUID();

    ArrayList<Semester> semesters = new ArrayList<Semester>();

    @BeforeEach
    public void setup() {

        courseList = CourseList.getInstance();
        courses = courseList.getCourses();
        // clear all courses from db
        courses.clear();
        semesters.add(Semester.Spring);
        semesters.add(Semester.Fall);

        // add couple of dummy courses
        courses.add(new Course("Course1", "subject1", "1", null, null, semesters, "description 1", 4, 'C',
                CourseState.NOT_STARTED,
                uuid1));
        courses.add(new Course("Course2", "subject2", "2", null, null, semesters, "description 2", 4, 'C',
                CourseState.NOT_STARTED,
                uuid2));
        // db should have only two courses for starting the testing

        System.out.println(courses);
        DataWriter.saveCourses(courses);

    }

    @AfterEach
    public void tearDown() {
        // at the end clear every thing
        // CourseList.getInstance().getCourses().clear();
        DataWriter.saveCourses(courses);
    }

    @Test
    // should be able to create
    public void testCreateCourse_DonotExists() {
        // add couple of dummy courses
        Course course3 = new Course("Course3", "subject3", "3", null, null, semesters, "description 3", 4, 'C',
                CourseState.NOT_STARTED,
                uuid3);

        boolean courseCreated = CourseList.getInstance().createCourse(course3);
        System.out.println("courseCreated  " + courseCreated);

        assertTrue(courseCreated);
    }

    @Test
    // should return false
    public void testCreateCourse_Exists() {
        // add couple of dummy courses
        Course course1 = new Course("Course1", "subject1", "1", null, null, semesters, "description 1", 4, 'C',
                CourseState.NOT_STARTED,
                uuid1);

        boolean courseCreated = CourseList.getInstance().createCourse(course1);
        assertFalse(courseCreated);
    }

    @Test
    // should be able to delete
    public void testDeleteCourse_Exists() {
        // create a course that exist
        Course course2 = new Course("Course2", "subject2", "2", null, null, semesters, "description 2", 4, 'C',
                CourseState.NOT_STARTED,
                uuid2);

        boolean courseDeleted = CourseList.getInstance().deleteCourse(course2);
        assertTrue(courseDeleted);
    }

    @Test
    // should return false
    public void testDeleteCourse_DonotExists() {
        // create a course that donot exist
        Course course4 = new Course("Course4", "subject4", "4", null, null, semesters, "description 4", 4, 'C',
                CourseState.NOT_STARTED,
                uuid4);

        boolean courseDeleted = CourseList.getInstance().deleteCourse(course4);
        assertFalse(courseDeleted);
    }

    @Test
    // should be able to update
    public void testEditCourse_Exists() {
        // create a course that donot exist
        Course updatedCourse1 = new Course("Course1", "subject1", "1", null, null, semesters, "updated description 1",
                4, 'C', CourseState.IN_PROGRESS,
                uuid1);

        boolean courseUpdated = CourseList.getInstance().editCourse(updatedCourse1);
        assertTrue(courseUpdated);
    }

    @Test
    // should return false
    public void testEditCourse_DonotExists() {
        // create a course that donot exist
        Course updatedCourse4 = new Course("Course4", "subject4", "4", null, null, semesters, "updated description 4",
                4, 'C', CourseState.IN_PROGRESS,
                uuid4);

        boolean courseUpdated = CourseList.getInstance().editCourse(updatedCourse4);
        assertFalse(courseUpdated);
    }

    @Test
    public void testFindCourse_Exists() {

        boolean courseFound = CourseList.getInstance().findCourse(uuid1);
        assertTrue(courseFound);

    }

    @Test
    public void testFindCourse_DonotExists() {

        boolean courseFound = CourseList.getInstance().findCourse(uuid3);
        assertFalse(courseFound);

    }

    @Test
    public void testFindCourseByName_Exists() {

        String courseName = "Course1";

        Course courseFound = CourseList.getInstance().findCourseByName(courseName);
        assertNotNull(courseFound);

    }

    @Test
    public void testFindCourseByName_DonotExists() {

        String courseName = "course5";

        Course courseFound = CourseList.getInstance().findCourseByName(courseName);
        assertNull(courseFound);

    }

    @Test
    public void testgetCourse_Exists() {

        Course courseFound = CourseList.getInstance().getCourse(uuid1);
        assertNotNull(courseFound);

    }

    @Test
    public void testgetCourse_DonotExists() {

        Course courseFound = CourseList.getInstance().getCourse(uuid4);
        assertNull(courseFound);

    }

    @Test
    public void testgetCourseBySubjectAndNumber_Exists() {

        String courseSubject = "subject1";
        String courseNumber = "1";

        Course courseFound = CourseList.getInstance().getCourse(courseSubject, courseNumber);
        assertNotNull(courseFound);

    }

    @Test
    public void testgetCourseBySubjectAndNumber_DonotExists() {
        String courseSubject = "subject5";
        String courseNumber = "5";

        Course courseFound = CourseList.getInstance().getCourse(courseSubject, courseNumber);
        assertNull(courseFound);

    }

}
