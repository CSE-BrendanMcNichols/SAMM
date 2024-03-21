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

public class testingFile{
    @Test
    public void makeNullCourse(){
        Course course = new Course(null, null,
        null, null,
        null, null,
        null, null, null,null);
        assertEquals(null, course.getCourseNumber());
        assertEquals(null, course.getCourseName());
        assertEquals(null, course.getCourseSubject());
        assertEquals(null, course.getCourseDescription());
        assertEquals(null, course.getCourseHours());
        assertEquals(null, course.getMinGrade());
        assertEquals(null, course.getCourseStatus());
        assertEquals(null, course.getUuid());
    }

    @Test
    public void getCourseNullUuid(){
        Course course = new Course();
        course.setUuid(null);
        assertEquals(null, course.getUuid());
    }

    @Test
    public void displayCourseWithNullCourseNumberAndNullCourseNameAndNullCourseSubject(){
        Course course = new Course();
        course.setCourseNumber(null);
        course.setCourseName(null);
        course.setCourseSubject(null);
        assertEquals(null, course.getCourseNumber());
        assertEquals(null, course.getCourseName());
        assertEquals(null, course.getCourseSubject());
    }

}