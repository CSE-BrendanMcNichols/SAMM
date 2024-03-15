package backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class DataWriterTester {

        public DataWriterTester() {

        }

        public static void main(String[] args) {
                // testSaveCourses();
                // testSaveStudents();
                // testSaveAdvisors();
                testSaveAdministrators();
                // testSaveMajors();

        }

        private static void testSaveAdvisors() {
                Advisor advisor1 = new Advisor("Mary1", "Ziemba1", "HE12865", "MaryZiemba1@email.sc.edu", "MaryZiemba1",
                                "P4zzWrd88881");

                Student student1 = new Student("Stan1", "Brown", "StanBrown1", "NoGuessing123",
                                "StanBrown1@email.sc.edu",
                                "KO84467");
                Student student2 = new Student("Jane1", "White", "JaneWhite1", "word123pass", "JaneWhite1@email.sc.edu",
                                "SE83331");
                advisor1.assignStudent(student1);
                advisor1.assignStudent(student2);

                Advisor advisor2 = new Advisor("Mary2", "Ziemba2", "HE128623", "MaryZiemba2@email.sc.edu",
                                "MaryZiemba2",
                                "P4zzWrd88881");

                Student student3 = new Student("Stan2", "Brown", "StanBrown2", "NoGuessing123",
                                "StanBrown2@email.sc.edu",
                                "KO84767");
                Student student4 = new Student("Jane2", "White", "JaneWhite2", "word123pass", "JaneWhite2@email.sc.edu",
                                "SE83090");
                advisor2.assignStudent(student3);
                advisor2.assignStudent(student4);

                ArrayList<Advisor> advisors = new ArrayList<Advisor>();
                advisors.add(advisor1);
                advisors.add(advisor2);
                DataWriter.saveAdvisors(advisors);

        }

        private static void testSaveAdministrators() {
                Administrator administrator1 = new Administrator("John", "Doe", "HE82021",
                                "JohnDoe@email.sc.edu", "JohnDoe",
                                "Password123");
                Administrator administrator2 = new Administrator("John", "Smith", "JY88081",
                                "JohnSmith@email.sc.edu", "JohnSmith",
                                "Password321");

                ArrayList<Administrator> administrators = new ArrayList<Administrator>();
                administrators.add(administrator1);
                administrators.add(administrator2);
                DataWriter.saveAdministrators(administrators);

        }

        private static void testSaveCourses() {

                Course course1 = new Course(UUID.randomUUID(), "CSCE 205 Business Applications Programming", 205,
                                null, null,
                                null,
                                "Introduction to computer applications in business. Programming exercises in COBOL",
                                3, 'C', 'C', CourseState.IN_PROGRESS);

                Course course2 = new Course(UUID.randomUUID(), "CSCE 145 Algorithmic Design I", 145,
                                null, null,
                                null,
                                "Basic college algebra; linear and quadratic equations, inequalities, functions and graphs of functions, exponential and logarithm functions, systems of equations",
                                4, 'C', 'A', CourseState.IN_PROGRESS);

                ArrayList<Course> courses = new ArrayList<Course>();
                courses.add(course1);
                courses.add(course2);

                DataWriter.saveCourses(courses);
        }
}
