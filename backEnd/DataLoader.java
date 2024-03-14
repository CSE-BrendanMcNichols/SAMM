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
import org.json.simple.parser.JSONParser;
import java.util.UUID;

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
				UUID uuid = UUID.fromString((String)studentJSON.get(UUIDSTRING));
				Year gradeYear = Year.StringToYear((String)studentJSON.get(GRADEYEAR));
				int credits = ((Long)studentJSON.get(CREDITS)).intValue();
				double overallGrade = (double)studentJSON.get(OVERALLGRADE);
				
				Advisor advisor = (Advisor)studentJSON.get(ADVISOR);
				Major major = (Major)studentJSON.get(MAJOR);
				ArrayList<Course> completedCourses = (ArrayList<Course>)studentJSON.get(COMPLETEDCOURSES);
				ArrayList<Course> currentCourses = (ArrayList<Course>)studentJSON.get(CURRENTCOURSES);
				ArrayList<String> notes = (ArrayList<String>)studentJSON.get(NOTES);

				students.add( new Student(username, password, email, uscid, gradeYear,
				advisor, major, overallGrade, credits,
				completedCourses, currentCourses, notes, uuid));
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
			JSONArray administratorsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < administratorsJSON.size(); i++) {
				JSONObject administratorJSON = (JSONObject)administratorsJSON.get(i);
				String username = (String)administratorJSON.get(USERNAME);
				String password = (String)administratorJSON.get(PASSWORD);
				String email = (String)administratorJSON.get(EMAIL);
				String uscid = (String)administratorJSON.get(USCID);
				String stringUUID = (String)administratorJSON.get(UUIDSTRING);
				UUID uuid = UUID.fromString(stringUUID);
				administrators.add(new Administrator(username, password, email, uscid, uuid));
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
			JSONArray advisorsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < advisorsJSON.size(); i++) {
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
	
	// working on this

	public static ArrayList<Course> getCoursesNoReq() {
		ArrayList<Course> courses = new ArrayList<Course>();

		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < coursesJSON.size(); i++) {
				JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
				String courseName = (String)courseJSON.get(COURSENAME);
				JSONArray semesters = (JSONArray) courseJSON.get(COURSESEMESTER);
				ArrayList<Semester> courseSemester = new ArrayList<Semester>();
				for(int j=0; j<semesters.size(); j++){
					String semester = (String) semesters.get(j);
        			courseSemester.add(Semester.StringToSemester(semester));
				}
				Long courseNumberLong = (Long)courseJSON.get(COURSENUMBER);
				int courseNumber = courseNumberLong.intValue();
				String courseDescription = (String)courseJSON.get(COURSEDESCRIPTION);
				Long courseHoursLong = (Long)courseJSON.get(COURSEHOURS);
				int courseHours = courseHoursLong.intValue();
				char minGrade = ((String)courseJSON.get(MINGRADE)).charAt(0);
				char userGrade = ((String)courseJSON.get(USERGRADE)).charAt(0);
				CourseState courseState = CourseState.StringToCourseState((String)courseJSON.get(COURSESTATUS));
				UUID uuid = UUID.fromString((String)courseJSON.get(UUIDSTRING));

				//ArrayList<Requirement> prerequisites = (ArrayList<Requirement>)courseJSON.get(PREREQUISITES);
				//ArrayList<Requirement> corequisites = (ArrayList<Requirement>)courseJSON.get(COREQUISITES);

				courses.add(new Course(courseName, courseSemester, courseNumber,
				courseDescription, courseHours, minGrade, userGrade,
				courseState, uuid));
			}
			
			return courses;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	public static ArrayList<Course> addReqs(ArrayList<Course> courses){
		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < coursesJSON.size(); i++) {
				JSONObject courseJSON = (JSONObject)coursesJSON.get(i);

				//ArrayList<Requirement> prerequisites = (ArrayList<Requirement>)courseJSON.get(PREREQUISITES);
				//ArrayList<Requirement> corequisites = (ArrayList<Requirement>)courseJSON.get(COREQUISITES);

				courses.get(i).addPrerequisite(null);
			}
			
			return courses;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	public static ArrayList<Course> getCourses(){
		ArrayList<Course> courses = getCoursesNoReq();
		getRequirements(courses);
		courses = addReqs(courses);
		return courses;
	}
	
	public static ArrayList<Requirement> getRequirements(ArrayList<Course> courses){
		ArrayList<Requirement> requirements = new ArrayList<Requirement>();

		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray requirementsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < requirementsJSON.size(); i++) {
				JSONObject requirementJSON = (JSONObject)requirementsJSON.get(i);
				Boolean eitherOr = Boolean.parseBoolean((String)requirementJSON.get(EITHEROR));
				RequirementType type = RequirementType.StringToType((String)requirementJSON.get(TYPE));
				String requirementFor = (String)requirementJSON.get(REQUIREMENTFOR);
				UUID uuid = UUID.fromString((String)requirementJSON.get(UUIDSTRING));

				JSONArray coursesJSON = (JSONArray) requirementJSON.get(COURSES);
                ArrayList<Course> reqCourses = new ArrayList<Course>();
				for(int j=0; j<coursesJSON.size(); j++){
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if(Course.findCourse(courses, uuid)){
						reqCourses.add(Course.getCourse(courses, courseUUID));
					}
					else{
						System.out.println("Missing course for requirement "+ requirementFor);
					}
				}
				//ArrayList<Requirement> prerequisites = (ArrayList<Requirement>)courseJSON.get(PREREQUISITES);

				requirements.add(new Requirement(reqCourses, eitherOr, type, requirementFor, uuid));
			}
			
			return requirements;
			
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