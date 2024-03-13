package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CourseList {
    private ArrayList<Course> courses;
    private HashMap<UUID, Course> coursesByUuid;
    private HashMap<String, UUID> uuidsByNameAndNumber;
    private static CourseList courseList;

    private CourseList() {
        courses = DataLoader.getCourses();
        coursesByUuid = new HashMap<>();
        uuidsByNameAndNumber = new HashMap<>();
        
        for (Course course : courses) {
            UUID uuid = course.getUuid();
            String key = course.getName() + " " + course.getCourseNumber();
            coursesByUuid.put(uuid, course);
            uuidsByNameAndNumber.put(key, uuid);
        }
    }

    public static CourseList getInstance() {
        if (courseList == null) {
            courseList = new CourseList();
        }
        return courseList;
    }

    public Course getCourseByUuid(UUID uuid) {
        return coursesByUuid.get(uuid);
    }

    public Course getCourseByNameAndNumber(String name, int number) {
        UUID uuid = uuidsByNameAndNumber.get(name + " " + number);
        return coursesByUuid.get(uuid);
    }

    public boolean addCourse(Course course) {
        String key = course.getName() + " " + course.getCourseNumber();
        if (!uuidsByNameAndNumber.containsKey(key)) {
            courses.add(course);
            UUID uuid = course.getUuid();
            coursesByUuid.put(uuid, course);
            uuidsByNameAndNumber.put(key, uuid);
            return true;
        } else {
            return false;
        }
    }
}
