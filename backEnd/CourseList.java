import java.util.ArrayList;
import java.util.UUID;

public class CourseList {
    private ArrayList<Course> courses;
    private static CourseList courseList;

    private CourseList() {
      loadCourses();
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

      public void addCourse(String name, Semester courseSemester, int courseNumber,  
                            ArrayList<Requirement> preRequisites, ArrayList<Requirement> coRequisites,
                            String courseDescription)
                        if (name = true   
                        || courseSemester == null
                        || courseNumber == null
                        || preRequisites == null
                        || coRequisites == null
                        || courseDescription == null
                        || type == null);

      public Course getCourseID(String id) {
          for (Course course: courses) {
            if(course.getCourseID().equals(id)) {
              return course;
            }
          }
      }

      private void loadCourses() {
        this.courses = DataLoader.getInstance().LoadCourses();
      }
}