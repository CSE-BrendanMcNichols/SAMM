package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CourseList {
    private ArrayList<Course> courses;
    private HashMap<UUID, Course> coursesByUuid;
    private HashMap<String, UUID> uuidsByNameAndNumber; // Use for combined name and number
    private static CourseList courseList;

    private CourseList() {
        courses = DataLoader.getCourses(); // Assuming DataLoader properly initializes courses
        coursesByUuid = new HashMap<>();
        uuidsByNameAndNumber = new HashMap<>();
        
        // Initialize maps with courses loaded from DataLoader
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

    // Method to get a course by its UUID
    public Course getCourseByUuid(UUID uuid) {
        return coursesByUuid.get(uuid);
    }

    // Method to get a course by combining its name and number
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