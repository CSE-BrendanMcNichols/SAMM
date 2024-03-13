package backEnd;

/**
 * This is a data loader for the program
 * 
 * @author Morgan
 */
import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants {

	public static ArrayList<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();

		
		try {
			FileReader reader = new FileReader(STUDENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray studentsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < studentsJSON.size(); i++) {
				JSONObject studentJSON = (JSONObject)studentsJSON.get(i);
				String username = (String)studentJSON.get(USERNAME);
				String password = (String)studentJSON.get(PASSWORD);
				String email = (String)studentJSON.get(EMAIL);
				String uscid = (String)studentJSON.get(USCID);
				String uuid = (String)studentJSON.get(UUID);
				String gradeYearString = (String)studentJSON.get(GRADEYEAR);
				Year gradeYear = Year.StringToYear(gradeYearString);
				Long creditsLong = (Long)studentJSON.get(CREDITS);
				int credits = creditsLong.intValue();
				double overallGrade = (double)studentJSON.get(OVERALLGRADE);
				Advisor advisor = (Advisor)studentJSON.get(ADVISOR);
				Major major = (Major)studentJSON.get(MAJOR);
				ArrayList<Course> completedCourses = (ArrayList<Course>)studentJSON.get(COMPLETEDCOURSES);
				ArrayList<Course> currentCourses = (ArrayList<Course>)studentJSON.get(CURRENTCOURSES);
				ArrayList<String> notes = (ArrayList<String>)studentJSON.get(NOTES);

				students.add( new Student(username, password, email, uscid, gradeYear,
				advisor, major, overallGrade, credits,
				completedCourses, currentCourses, notes));
			}
			
			return students;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	
	public static ArrayList<Administrator> getAdministrators() {
		ArrayList<Administrator> administrators = new ArrayList<Administrator>();

		
		try {
			FileReader reader = new FileReader(ADMINISTRATOR_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray administratorJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < administratorJSON.size(); i++) {
				JSONObject administratorJSON = (JSONObject)administratorJSON.get(i);
				String username = (String)administratorJSON.get(USERNAME);
				String password = (String)administratorJSON.get(PASSWORD);
				String email = (String)administratorJSON.get(EMAIL);
				String uscid = (String)administratorJSON.get(USCID);
				
				administrators.add( new Administrator(username, password, email, uscid));
			}
			
			return administrators;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Advisor> getAdvisors() {
		ArrayList<Advisor> advisors = new ArrayList<Advisor>();

		
		try {
			FileReader reader = new FileReader(ADVISOR_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray advisorJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < advisorJSON.size(); i++) {
				JSONObject advisorJSON = (JSONObject)advisorJSON.get(i);
				String username = (String)advisorJSON.get(USERNAME);
				String password = (String)advisorJSON.get(PASSWORD);
				String email = (String)advisorJSON.get(EMAIL);
				String uscid = (String)advisorJSON.get(USCID);
				ArrayList<Student> assignedStudents = (ArrayList<Student>)advisorJSON.get(ASSIGNED_STUDENTS);
				
				advisors.add( new Advisor(username, password, email, uscid, assignedStudents));
			}
				
			return advisors;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public static ArrayList<Administrator> getAdministrators() {
		ArrayList<Administrator> administrators = new ArrayList<Administrator>();
		
		try {
			FileReader reader = new FileReader(ADMINISTRATOR_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray administratorJSON = (JSONArray)new JSONParser().parse(reader);
				
			for(int i=0; i < administratorJSON.size(); i++) {
				JSONObject administratorJSON = (JSONObject)administratorJSON.get(i);
				String username = (String)administratorJSON.get(USERNAME);
				String password = (String)administratorJSON.get(PASSWORD);
				String email = (String)administratorJSON.get(EMAIL);
				String uscid = (String)administratorJSON.get(USCID);
				
				administrators.add( new Administrator(username, password, email, uscid));
			}
				
			return administrators;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// working on this

	public static ArrayList<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();

		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < coursesJSON.size(); i++) {
				JSONObject courseJSON = (JSONObject)courseJSON.get(i);
				String courseName = (String)courseJSON.get(COURSENAME);
				String courseSemesterString = (String)courseJSON.get(COURSESEMESTER);
				Semester courseSemester = Semester.StringToSemester(courseSemesterString);
				Long courseNumberLong = (Long)courseJSON.get(COURSENUMBER);
				int courseNumber = courseNumberLong.intValue();
				String courseDescription = (String)courseJSON.get(COURSEDESCRIPTION);
				Long courseHoursLong = (Long)courseJSON.get(COURSEHOURS);
				int courseHours = courseHoursLong.intValue();
				char minGrade = (char)courseJSON.get(MINGRADE);
				char userGrade = (char)courseJSON.get(USERGRADE);
				String courseStatusString = (String)courseJSON.get(COURSESTATUS);
				ArrayList<Requirement> prerequisites = (ArrayList<Requirement>)courseJSON.get(PREREQUISITES);
				ArrayList<Requirement> corequisites = (ArrayList<Requirement>)courseJSON.get(COREQUISITES);

				courses.add(new Course(username, password, email, uscid, assignedStudents));
			}
			
			return courses;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	/**
	 * This method returns the MajorList by reading from the major list json file
	 * @return
	 */
	public static ArrayList<Major> getMajorList() {

		ArrayList<Major> majors = new ArrayList<Major>();

		try {
			FileReader reader = new FileReader(DataConstants.MAJORS_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray majorssJSONObjects = (JSONArray)parser.parse(reader);
			
			for(int i=0; i < majorssJSONObjects.size(); i++) {

				JSONObject majorJSON = (JSONObject)majorssJSONObjects.get(i);

				String majorString = (String)majorJSON.get(DataConstants.MAJOR);
				Major major = new Major(majorString);
				
				//TODO: Only getting "major" for now. need to load the rest of the json elemnts into the respective classes.

				majors.add(major);
			}
			
			return majors;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


}
//Made no changes