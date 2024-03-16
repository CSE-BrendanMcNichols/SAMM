package backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class Cache {

    private static Cache cacheInstance; // Singleton instance of Cache

    private static UserList userListInsance;
    private static MajorList majorListInstance;
    private static CourseList courseListInstance;

    private static ArrayList<Elective> electiveList = new ArrayList<Elective>();
    private static ArrayList<Requirement> requirementList = new ArrayList<Requirement>();

    public static Cache getInstance() {
        if (cacheInstance == null) {
            cacheInstance = new Cache();
        }
        return cacheInstance;
    }

    /**
     * Private constructor to prevent instantiation.
     * Initializes the list of users.
     */
    private Cache() {
        userListInsance = UserList.getInstance();
    }


    public void initializeCourses() {
        courseListInstance = CourseList.getInstance();
        for (Course course: courseListInstance.getCourses()) {
            //System.out.println(course + "\n");
        }
    }
    public void initializeRequirements() {
        requirementList = DataLoader.loadRequirements();

        for (Requirement req: requirementList) {
            //System.out.println(req+"\n");
        }
        
    }

    public void initializeCourseRequirements() {
        DataLoader.loadCoursesWithRequirements();
        for (Course course: courseListInstance.getCourses()) {
            //System.out.println(course+"\n");
        }

    }

    public void initializeElectives() {
        electiveList = DataLoader.loadElectives();
        for (Elective elective: electiveList) {
            //System.out.println(elective+"\n");
        }
    }
    public void initializeMajors() {
        majorListInstance = MajorList.getInstance();
        for (Major major: majorListInstance.getMajors()) {
            //System.out.println(major+"\n");
        }
    }

    public void initializeAdminstrators() {
        userListInsance.initializeAdminstrators();
        for (User user: userListInsance.getUsers()) {
            //System.out.println(user+"\n");
        }
    }

    public void initializeStudentsNoAdvisor() {
        userListInsance.initializeStudentsNoAdvisor();
        for (Student student: userListInsance.getStudents()) {
            //System.out.println(student+"\n");
        }
    }

    public void initializeAdvisors() {
        userListInsance.initializeAdvisors();
        for (Advisor advisor: userListInsance.getAdvisors()) {
            //System.out.println(advisor+"\n");
        }
    }

    public void initializeStudentsAdvisor() {
        DataLoader.loadStudentsAdvisor();
        for (Student student: userListInsance.getStudents()) {
            //System.out.println(student+"\n");
        }

    }


    

    


    


    public void initialize() {

        // Load Courses Objects in 2 phases
        // 1. Load Courses without Requirements
        // 2. Load Requirements with references to the above Course Objects [ object
        // references ]
        // 3. reload Course objects - prerequisites and corequisites with references to
        // the above Requirements objects

        courseListInstance = CourseList.getInstance();

        ArrayList<Course> courses = DataLoader.loadCoursesMinusRequirements();
        requirementList = DataLoader.loadRequirements();
        // DataLoader.loadCoursesWithRequirements();

        courseListInstance = CourseList.getInstance();
        courseListInstance.setCourses(courses);

        // electiveList = DataLoader.loadElectives();
        // majorListInstance = MajorList.getInstance();
        // userListInsance = UserList.getInstance();
    }

    public Boolean findStudent(UUID uuid){
        for (Student student : userListInsance.getStudents()){
            if(student.getUuid().equals(uuid)){
                return true;
            }
        }
        return false;
    }
    public Student getStudent(UUID uuid){
        for (Student student : userListInsance.getStudents()){
            if(student.getUuid().equals(uuid)){
                return student;
            }
        }
        return null;
    }

    public Boolean findAdvisor(UUID uuid){
        for (Advisor advisor : userListInsance.getAdvisors()){
            if(advisor.getUuid().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public Advisor getAdvisor(UUID uuid){
        for (Advisor advisor : userListInsance.getAdvisors()){
            if(advisor.getUuid().equals(uuid)){
                return advisor;
            }
        }
        return null;
    }

    public Boolean findAdministrator(UUID uuid){
        for (Administrator administrator : userListInsance.getAdministrators()){
            if(administrator.getUuid().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public Administrator getAdministrator(UUID uuid){
        for (Administrator administrator : userListInsance.getAdministrators()){
            if(administrator.getUuid().equals(uuid)){
                return administrator;
            }
        }
        return null;
    }

    public Boolean findCourse(UUID uuid) {
        for (Course course : courseListInstance.getCourses()) {
            if (course.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Course getCourse(UUID uuid) {
        for (Course course : courseListInstance.getCourses()) {
            if (course.getUuid().equals(uuid)) {
                return course;
            }
        }
        return null;
    }

    public Boolean findElective(UUID uuid) {
        for (Elective elective : electiveList) {
            if (elective.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Elective getElective(UUID uuid) {
        for (Elective elective : electiveList) {
            if (elective.getUuid().equals(uuid)) {
                return elective;
            }
        }
        return null;
    }

    public Boolean findRequirement(UUID uuid) {
        for (Requirement requirement : requirementList) {
            if (requirement.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Requirement getRequirement(UUID uuid) {
        for (Requirement requirement : requirementList) {
            if (requirement.getUuid().equals(uuid)) {
                return requirement;
            }
        }
        return null;
    }

    public Boolean findMajor(UUID uuid) {
        for (Major major : majorListInstance.getMajors()) {
            if (major.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
    

    public  Major getMajor(UUID uuid) {
        for (Major major : majorListInstance.getMajors()) {
            if (major.getUuid().equals(uuid)) {
                return major;
            }
        }
        return null;
    }

    

}