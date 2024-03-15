package backEnd;

/**
 * This is a data loader for the program
 * 
 * @author Morgan
 */
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.UUID;

public class DataLoader extends DataConstants {

	public static ArrayList<Course> getCourses(){
		ArrayList<Course> courses = getCoursesNoReq();
		ArrayList<Requirement> requirements = getRequirements(courses);
		courses = addReqs(courses, requirements);
		requirements = getRequirements(courses);
		courses = addReqs(courses, requirements);
		return courses;
	}

	public static ArrayList<Requirement> getRequirements(){
		ArrayList<Course> courses = getCoursesNoReq();
		ArrayList<Requirement> requirements = getRequirements(courses);
		courses = addReqs(courses, requirements);
		requirements = getRequirements(courses);
		return requirements;
	}

	public static ArrayList<Elective> getElectives(){
		ArrayList<Course> courses = getCourses();
		ArrayList<Elective> electives = getElective(courses);
		return electives;
	}

	public static ArrayList<Major> getMajors(){
		ArrayList<Course> courses = getCourses();
		ArrayList<Elective> electives = getElectives();
		ArrayList<Major> majors = getMajor(courses, electives);
		return majors;
	}

	public static ArrayList<Student> getStudents(){
		ArrayList<Elective> electives = getElectives();
		ArrayList<Major> majors = getMajors();
		ArrayList<Student> students = getStudentNoAdvisor();
		//ArrayList<Advisor> advisors = getAdvisor(students);
		//students = addAdvisors(students, advisors);
		//advisors = getAdvisor(students);
		//students = addAdvisors(students, advisors);
		return students;
	}

	public static ArrayList<Advisor> getAdvisors(){
		ArrayList<Elective> electives = getElectives();
		ArrayList<Major> majors = getMajors();
		ArrayList<Student> students = getStudentNoAdvisor(majors);
		ArrayList<Advisor> advisors = getAdvisor(students);
		students = addAdvisors(students, advisors);
		advisors = getAdvisor(students);
		return advisors;
	}

	public static ArrayList<Major> getMajor(ArrayList<Course> courses, ArrayList<Elective> electives){
		ArrayList<Major> majors = new ArrayList<Major>();

		try {
			FileReader reader = new FileReader(MAJORS_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray majorsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < majorsJSON.size(); i++) {
				JSONObject majorJSON = (JSONObject)majorsJSON.get(i);
				String major = (String)majorJSON.get(MAJOR);
				UUID uuid = UUID.fromString((String)majorJSON.get(UUIDSTRING));
				JSONArray coursesJSON = (JSONArray) majorJSON.get(COURSES);
                ArrayList<Course> electiveCourses = new ArrayList<Course>();
				for(int j=0; j<coursesJSON.size(); j++){
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if(Course.findCourse(courses, courseUUID)){
						electiveCourses.add(Course.getCourse(courses, courseUUID));
					}
					else{
						System.out.println("Missing course for Major "+ major);
					}
				}
				Elective majorElective = new Elective();
				UUID electiveUuid = UUID.fromString((String)majorJSON.get(ELECTIVE_COURSES));
				for(Elective elective : electives){
					if(electiveUuid.equals(elective.getUuid())){
						majorElective = elective;
					}
				}
				
				JSONObject coreReqJSON = (JSONObject) majorJSON.get("coreReq");
				HashMap<RequirementType, Integer> coreReq = new HashMap<>();
				for (Object key : coreReqJSON.keySet()) {
   					RequirementType coreReqName = RequirementType.StringToType(((String) key));
					int coreReqValue = Integer.parseInt(coreReqJSON.get(coreReqName.toString()).toString());
    				coreReq.put(coreReqName, coreReqValue);
				}

				majors.add(new Major( electiveCourses, coreReq, majorElective, major, uuid));
			}
			
			return majors;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	public static ArrayList<Student> getStudentNoAdvisor(ArrayList<Major> majors) {
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
				
				//Advisor advisor = (Advisor)studentJSON.get(ADVISOR);
				Major major = (Major)studentJSON.get(MAJOR);

				HashMap<Course, String> completedCourses = (HashMap<Course, String>)studentJSON.get(COMPLETEDCOURSES);
				ArrayList<Course> currentCourses = (ArrayList<Course>)studentJSON.get(CURRENTCOURSES);
				ArrayList<String> notes = (ArrayList<String>)studentJSON.get(NOTES);

				students.add( new Student());
			}
			
			return students;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Elective> getElective(ArrayList<Course> courses){
		ArrayList<Elective> electives = new ArrayList<Elective>();

		try {
			FileReader reader = new FileReader(ELECTIVE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray electivesJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < electivesJSON.size(); i++) {
				JSONObject electiveJSON = (JSONObject)electivesJSON.get(i);
				String electiveName = (String)electiveJSON.get(ELECTIVENAME);
				int hours = ((Long)electiveJSON.get(HOURS)).intValue();
				UUID uuid = UUID.fromString((String)electiveJSON.get(UUIDSTRING));
				JSONArray coursesJSON = (JSONArray) electiveJSON.get(COURSES);
                ArrayList<Course> electiveCourses = new ArrayList<Course>();
				for(int j=0; j<coursesJSON.size(); j++){
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if(Course.findCourse(courses, courseUUID)){
						electiveCourses.add(Course.getCourse(courses, courseUUID));
					}
					else{
						System.out.println("Missing course for Elective "+ electiveName);
					}
				}
				electives.add(new Elective(electiveCourses, electiveName, hours, uuid));
			}
			
			return electives;
			
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

	public static ArrayList<Advisor> getAdvisor(ArrayList<Student> students) {
		ArrayList<Advisor> advisors = new ArrayList<Advisor>();

		
		try {
			FileReader reader = new FileReader(ADVISOR_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray advisorsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < advisorsJSON.size(); i++) {
				JSONObject advisorJSON = (JSONObject)advisorsJSON.get(i);
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
				int courseNumber = ((Long)courseJSON.get(COURSENUMBER)).intValue();
				String courseDescription = (String)courseJSON.get(COURSEDESCRIPTION);
				int courseHours = ((Long)courseJSON.get(COURSEHOURS)).intValue();
				char minGrade = ((String)courseJSON.get(MINGRADE)).charAt(0);
				CourseState courseState = CourseState.StringToCourseState((String)courseJSON.get(COURSESTATUS));
				UUID uuid = UUID.fromString((String)courseJSON.get(UUIDSTRING));

				courses.add(new Course(courseName, courseSemester, courseNumber,
				courseDescription, courseHours, minGrade,
				courseState, uuid));
			}
			
			return courses;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	public static ArrayList<Course> addReqs(ArrayList<Course> courses, ArrayList<Requirement> requirements){
		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < coursesJSON.size(); i++) {
				JSONObject courseJSON = (JSONObject)coursesJSON.get(i);

				JSONArray prereqsJSON = (JSONArray) courseJSON.get(PREREQUISITES);
                ArrayList<Requirement> reqCourses = new ArrayList<Requirement>();
				for(int j=0; j<prereqsJSON.size(); j++){
					UUID courseUUID = UUID.fromString((String) prereqsJSON.get(j));
					if(Requirement.findReq(requirements, courseUUID)){
						reqCourses.add(Requirement.getReq(requirements, courseUUID));
					}
					else{
						System.out.println("Missing Req");
					}
				}

				JSONArray coreqsJSON = (JSONArray) courseJSON.get(COREQUISITES);
                ArrayList<Requirement> coreqCourses = new ArrayList<Requirement>();
				for(int j=0; j<coreqsJSON.size(); j++){
					UUID courseUUID = UUID.fromString((String) coreqsJSON.get(j));
					if(Requirement.findReq(requirements, courseUUID)){
						coreqCourses.add(Requirement.getReq(requirements, courseUUID));
					}
					else{
						System.out.println("Missing Req");
					}
				}

				for(Requirement requirement : reqCourses){
					courses.get(i).addPrerequisite(requirement);
				}

				for(Requirement requirement : coreqCourses){
					courses.get(i).addPrerequisite(requirement);
				}
			}
			
			return courses;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}
	
	public static ArrayList<Requirement> getRequirements(ArrayList<Course> courses){
		ArrayList<Requirement> requirements = new ArrayList<Requirement>();

		try {
			FileReader reader = new FileReader(REQUIREMENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray requirementsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < requirementsJSON.size(); i++) {
				JSONObject requirementJSON = (JSONObject)requirementsJSON.get(i);
				Boolean eitherOr = (Boolean)requirementJSON.get(EITHEROR);
				RequirementType type = RequirementType.StringToType((String)requirementJSON.get(TYPETEST));
				String requirementFor = (String)requirementJSON.get(REQUIREMENTFOR);
				UUID uuid = UUID.fromString((String)requirementJSON.get(UUIDSTRING));
				JSONArray coursesJSON = (JSONArray) requirementJSON.get(COURSES);
                ArrayList<Course> reqCourses = new ArrayList<Course>();
				for(int j=0; j<coursesJSON.size(); j++){
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if(Course.findCourse(courses, courseUUID)){
						reqCourses.add(Course.getCourse(courses, courseUUID));
					}
					else{
						System.out.println("Missing course for requirement "+ requirementFor);
					}
				}
				requirements.add(new Requirement(reqCourses, eitherOr, type, requirementFor, uuid));
			}
			
			return requirements;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
}
//Made no changes