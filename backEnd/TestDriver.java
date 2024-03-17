package backEnd;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TestDriver {

    private static ApplicationFacade applicationFacade;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean loggedIn = false;

    public TestDriver() {
        // facade = new SchedulingFacade;
        applicationFacade = ApplicationFacade.getInstance();
    }

    public void run() {
        scenario1();
        scenario2();
    }

    public static void main(String[] args) {
        applicationFacade = ApplicationFacade.getInstance();
        // scenario1();
        scenario2();

    }

    public static void scenario1() {

        Student braxWest = new Student("Brax", "West", "12345", "brax@email.sc.edu", "brax_west", "password",
                UserType.STUDENT, Year.Junior, null, null, 0.0, 0, null, null, null);

        checkProgress(braxWest);
        generateSemesterPlan(braxWest);
    }

    public static void scenario2() {
        // scenario 2 code

        /*
         * Course c1 = new Course("CS101", 3, "Spring 2023", "TTh 10:30-11:45",
         * "Instructor 1");
         * Course c2 = new Course("CS202", 3, "Fall 2022", "MWF 10:00-10:50",
         * "Instructor 2");
         * Course c3 = new Course("C3", 3, "Fall 2022", "MWF 10:00-10:50",
         * "Instructor 2");
         * ArrayList<Course> current = new ArrayList<Course>();
         * current.add(c1);
         * current.add(c2);
         * Elective Stats1 = new Elective(current, "Stats1", 4, null);
         * Elective Stats2 = new Elective(current, "Stats2", 3, null);
         * Student s1 = new Student("Twanie", "Hill", "111", "Twanie.Hill@email.com",
         * "twaniehill", "pass", UserType.STUDENT, Year.Junior, null, null,
         * 0, 0, null, null, null);
         * s1.setApplicationArea("Not Stats");
         * s1.setCurrentCourses(current);
         * HashMap<Course, String> complete = new HashMap<Course, String>();
         * complete.put(c3, "Passed");
         * s1.setCompletedCourses(complete);
         * s1.addElective(Stats1);
         * s1.addElective(Stats2);
         */
        // Start the program
        Advisor advisor = null;
        while (true) {
            System.out.println("\nWelcome to the Application");
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. View Student Information");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    advisor = login();
                    break;
                case 2:
                    advisor = createAdvisorAccount();
                    break;
                case 3:
                    viewStudentInfo(advisor);
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * Login method sets the login as true for successful login
     */
    private static Advisor login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = applicationFacade.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful!");
            loggedIn = true;
            return UserList.getInstance().getAdvisor(user.getUuid());
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
        return null;
        
    }

    /**
     * Save the info and logout
     */
    private static void logout() {
        System.out.print("Saving Information...");
        DataWriter.saveAdvisors(UserList.getInstance().getAdvisors());
        DataWriter.saveStudents(UserList.getInstance().getStudents());
        System.out.print("Your are successfully logged out! Good Bye!");
        System.exit(0);
    }

    /**
     * Create new Advisor account
     * @return
     */
    private static Advisor createAdvisorAccount() {

        System.out.println("Enter username for your account.");
        String username = scanner.nextLine();

        System.out.println("Enter password for your account.");
        String password = scanner.nextLine();
        // Logic to add it to dataloader
        System.out.println("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter USC ID: ");
        String uscid = scanner.nextLine();

        System.out.println("Enter Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter Department: ");
        String department = scanner.nextLine();

        Advisor advisor = new Advisor(firstName, lastName, uscid, email, username, password, department);
        // Add advisor to the cache.
        UserList.getInstance().addAdvisor(advisor);

        System.out.println("Advisor Account successfully created. You can now login.");
        loggedIn = false; // need to login to continue with new account.
        return advisor;
    }

    /**
     * View Student info
     * @param advisor
     */
    private static void viewStudentInfo(Advisor advisor) {
        if (loggedIn == false) {
            System.out.println("Please Login to proceed.");
            return;
        }

        while (true) {
            System.out.println("Which Student you would like to work with ? Enter his/her USCID:");
            UserList.getInstance().displayStudents();
            String studentId = scanner.nextLine();

            // Search for the student id in the cache.
            User user = UserList.getInstance().getUserByUscId(studentId);
            //System.out.println("Matching User " + user);
            if (user == null) {
                System.out.println("\nStudent Id:" + studentId + " not found in the database. Please Retry!");
                continue;
            } else {
                Student student = UserList.getInstance().getStudent(user.getUuid());
                student.setAdvisor(advisor);
                advisor.assignStudent(student);

                System.out.println("\nYour are now assigned as an Advisor to Student []" + student.getFirstName() + "] "
                        + student.getLastName());
                System.out.println("\nWould like to see current progress of " + student.getFirstName() + " "
                        + student.getLastName() + " ?");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    student.viewCurrentSchedule();
                    System.out.println(
                            "\nWould like to add a note for " + student.getFirstName() + student.getLastName() + " ?");
                    response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        String note = scanner.nextLine();
                        //String note = "You have taken 2 elective stats classes but havent declared stats as your application area. I reccomment making stats your application area.";
                        student.addNotes(note);                        
                    } else {
                        System.out.println("Good bye!");
                    }
                    break;
                } else {
                    System.out.println("Good bye!");
                    break;
                }
            }
        }
    }

    
    public static void testLoader() {
        DataLoader dataLoader = new DataLoader();

        ArrayList<Student> electives = DataLoader.loadStudentsNoAdvisor();
        // System.out.println
        System.out.println(electives.get(0).getFirstName());
    }

    private static void checkProgress(Student braxWest) {
        System.out.println("Brax West's Completed Courses: ");
        for (Course course : braxWest.getCurrentCourses()) {
            System.out.println(course.getCourseSubject() + " " + course.getCourseNumber() + " Grade: "
                    + braxWest.getCompletedCourses().get(course));
        }
        System.out.println("\nBrax West's Remaining Courses:");
        for (Course course : braxWest.getCurrentCourses()) {
            System.out.println(course.getCourseSubject() + " " + course.getCourseNumber());
        }
    }

    // TODO: Implement method to select GFL elective and choose Digital Design

    private static void generateSemesterPlan(Student braxWest) {
        try {
            FileWriter writer = new FileWriter("BraxWest_SemesterPlan.txt");
            writer.write("Brax West's 8-Semester Plan:\n\n");
            ArrayList<Course> coursesToTake = new ArrayList<>(braxWest.getCurrentCourses());
            for (int i = 1; i <= 8; i++) {
                writer.write("Semester " + i + ":\n");
                writer.write("Courses to Take:\n");
                for (Course course : coursesToTake) {
                    writer.write(course.getCourseSubject() + " " + course.getCourseNumber() + " - "
                            + course.getCourseName() + "\n");
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println(
                    "\n8-Semester Plan for Brax West has been generated and saved to BraxWest_SemesterPlan.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the 8-Semester Plan to a file.");
            e.printStackTrace();
        }
    }
}