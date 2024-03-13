package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CourseList {
    private ArrayList<Course> courses;
    private HashMap<UUID, Course> coursesByUuid;
    private HashMap<String, UUID> uuidsByName;
    private static CourseList courseList;

    private CourseList() {
        courses = DataLoader.getCourses();
        coursesByUuid = new HashMap<>();
        uuidsByName = new HashMap<>();
        
        for (Course course : courses) {
            UUID uuid = course.getUuid();
            String key = course.getName(); 
            coursesByUuid.put(uuid, course);
            uuidsByName.put(key, uuid);
        }
    }

    public static CourseList getInstance() {
        if (courseList == null) {
            courseList = new CourseList();
        }
        return courseList;
    }

    public Course getCourse(String name) {
        UUID uuid = uuidsByName.get(name);
        return coursesByUuid.get(uuid);
    }

    public boolean addCourse(Course course) {
        if (!uuidsByName.containsKey(course.getName())) {
            courses.add(course);
            UUID uuid = course.getUuid();
            String key = course.getName(); // Use course name as the key
            coursesByUuid.put(uuid, course);
            uuidsByName.put(key, uuid);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteCourse(Course course) {
        if (courses.remove(course)) {
            UUID uuid = course.getUuid();
            String key = course.getName(); // Use course name as the key
            coursesByUuid.remove(uuid);
            uuidsByName.remove(key);
            return true;
        } else {
            return false;
        }
    }
  }