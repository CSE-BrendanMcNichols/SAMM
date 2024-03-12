import java.util.ArrayList;
import java.util.UUID;

public class CourseList {
    private ArrayList<Course> courses;
    private static CourseList courseList;

    private CourseList() {
        courses = DataLoader.loadCourses();
    }

    public static CourseList getInstance() {
        if(courseList == null) {
            courseList = new CourseList();
        }
        return courseList;
    }

    public Course getCourse(String name) {
        for (Course course : courses) {
          if (course.getCourseName().equals(name)) {
            return course;
          }
        }
        return null;
      }