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
        //made by Matt
    }

    @Test
    public void getCourseNullUuid(){
        Course course = new Course();
        course.setUuid(null);
        assertEquals(null, course.getUuid());
        //made by Matt
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
        //made by Matt
    }

    @Test
    public void courseAddNullPrerequisite(){
        Course course = new Course();
        course.addPrerequisite(null);
        assertEquals(null, course.getPrerequisites());
        //made by Matt
    }

    @Test
    public void courseAddNullCorequisite(){
        Course course = new Course();
        course.addCorequisite(null);
        assertEquals(null, course.getCorequisites());
        //made by Matt
    }

    @Test
    public void courseRemovePrerequisiteThatItDoesntHave(){
        Course course = new Course();
        Requirement requirement1 = new Requirement();
        Requirement requirement2 = new Requirement();
        course.addPrerequisite(requirement1);
        course.addPrerequisite(requirement2);
        Requirement requirement3 = new Requirement();
        course.removePrerequisite(requirement3);
        assertEquals(requirement1, course.getPrerequisites().get(0));
        assertEquals(requirement2, course.getPrerequisites().get(1));
        //made by Matt
    }

}