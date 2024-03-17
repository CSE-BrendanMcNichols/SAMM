package backEnd;

import java.util.Scanner;

public class ApplicationUI {
    private static ApplicationFacade applicationFacade;
    private static boolean loggedIn = false;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        applicationFacade = ApplicationFacade.getInstance();
        User user = null;
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
                    user = login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    viewStudentInfo(user);
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }


    private static User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = applicationFacade.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful! You are logged in as " + UserType.getTypeString(user.getType()));
            loggedIn = true;
            return user;
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
        return null;
        
    }

    private static void createAccount() {
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
        
        System.out.println("Are you a Student, Advisor, or Administrator?");
        String userTypeString = scanner.nextLine();
        UserType userType = UserType.valueOf(userTypeString.toUpperCase());
        
        System.out.println("Enter Department: ");
        String department = scanner.nextLine();

        User user = applicationFacade.registerUser(userType, firstName, lastName, uscid, email, username, password,department);
        if (user != null) {
            System.out.println("Account successfully created. You can now login.");
        } else {
            System.out.println("Account creation failed. User may already exist.");
        }
    }

    /**
     * View Student info
     * @param advisor
     */
    private static void viewStudentInfo(User loggedInUser) {
        if (loggedIn == false) {
            System.out.println("Please Login to proceed.");
            return;
        }
        //System.out.println("loggedInUser" + loggedInUser);

        if (loggedInUser.getType() == UserType.ADVISOR) {
            Advisor advisor = UserList.getInstance().getAdvisor(loggedInUser.getUuid());
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

                    System.out.println("\nYour are now assigned as an Advisor to Student - " + student.getFirstName() + " "
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

}