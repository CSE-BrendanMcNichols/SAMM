package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CourseList {
    private ArrayList<Course> courses;
    private HashMap<UUID, Course> coursesByUuid;
    private HashMap<String, UUID> uuidsBySubjectAndNumber;
    private static CourseList courseList;

    private CourseList() {
        courses = DataLoader.loadCoursesMinusRequirements();
        coursesByUuid = new HashMap<>();
        uuidsBySubjectAndNumber = new HashMap<>();
    }

    public static CourseList getInstance() {
        if (courseList == null) {
            courseList = new CourseList();
        }
        return courseList;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }


    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public Course getCoursesByUuid(UUID uuid) {
        return coursesByUuid.get(uuid);
    }

    public Course getCourse(String subject, int number) {
        UUID uuid = uuidsBySubjectAndNumber.get(subject + " " + number);
        return coursesByUuid.get(uuid);
    }

    public boolean createCourse(Course course) {
        if (!coursesByUuid.containsKey(course.getUuid())) {
            courses.add(course);
            coursesByUuid.put(course.getUuid(), course);
            uuidsBySubjectAndNumber.put(course.getCourseSubject() + " " + course.getCourseNumber(), course.getUuid());
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteCourse(Course course) {
        if (courses.remove(course)) {
            coursesByUuid.remove(course.getUuid());
            uuidsBySubjectAndNumber.remove(course.getCourseSubject() + " " + course.getCourseNumber());
            return true;
        } else {
            return false;
        }
    }

    public boolean editCourse(Course course) {
        if (!coursesByUuid.containsKey(course.getUuid())) {
            return false;
        }

        Course originalCourse = coursesByUuid.get(course.getUuid());
        courses.remove(originalCourse);
        uuidsBySubjectAndNumber.remove(originalCourse.getCourseSubject() + " " + originalCourse.getCourseNumber());

        courses.add(course);
        coursesByUuid.put(course.getUuid(), course);
        uuidsBySubjectAndNumber.put(course.getCourseSubject() + " " + course.getCourseNumber(), course.getUuid());

        return true;
    }

    public Boolean findCourse(UUID uuid) {
        for (Course course : courses) {
            if (course.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Course getCourse(UUID uuid) {
        for (Course course : courses) {
            if (course.getUuid().equals(uuid)) {
                return course;
            }
        }
        return null;
    }
}
