package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class TestDriver {
    private ApplicationFacade facade;

    TestDriver(){
        //facade = new SchedulingFacade;
    }
    public void run(){
        scenario1();
        //scenario2();
    }
    public static void main(String[] args) {
        //scenario1();






        //scenario 2 code
        Course c1 = new Course("CS101", 3, "Spring 2023", "TTh 10:30-11:45", "Instructor 1");
        Course c2 = new Course("CS202", 3, "Fall 2022", "MWF 10:00-10:50", "Instructor 2");
        Course c3 = new Course("C3", 3, "Fall 2022", "MWF 10:00-10:50", "Instructor 2");
        Elective Stats1 = new Elective();
        Elective Stats2 = new Elective();
        Student s1 = new Student("Twanie", "Hill", "111", "email@email.com", "null", "pass", null, null, null, null, 0, 0, null, null, null);
        s1.addElective(Stats1);
        s1.addElective(Stats2);
        s1.setApplicationArea("Not Stats");

        //Start the program
        System.out.println("Welcome. Please enter your username to log in.");
        System.out.println("If you wish to create a new account, please type NEWACCOUNT.");
        System.out.println("If you wish to quit the program, please type QUIT.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(input.equals("NEWACCOUNT")){
            System.out.println("Please enter your account username.");
            String newuserusername = scanner.nextLine();
            System.out.println("Please enter your account password.");
            String newuserpassword = scanner.nextLine();
            //Logic to add it to dataloader
            
        }else if(input.equals("QUIT")){
            System.out.println("Goodbye!");
            System.exit(0);
        }else{
            //Logic to check if account exists already
        }
        //end of scenario 2 code
        
    }
    public static void scenario1(){
        
        DataLoader dataLoader = new DataLoader();

        ArrayList<Major> electives = DataLoader.getMajors();

        electives.get(0).displaycoreReq();
    }
}