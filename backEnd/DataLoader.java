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

public class DataLoader {

	public static ArrayList<Student> loadStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
<<<<<<< HEAD

		try {
			FileReader reader = new FileReader(DataConstants.STUDENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray studentsJSON = (JSONArray) new JSONParser().parse(reader);

			for (int i = 0; i < studentsJSON.size(); i++) {
				JSONObject studentJSON = (JSONObject) studentsJSON.get(i);
				String username = (String) studentJSON.get(DataConstants.USERNAME);
				String password = (String) studentJSON.get(DataConstants.PASSWORD);
				String email = (String) studentJSON.get(DataConstants.EMAIL);
				String uscid = (String) studentJSON.get(DataConstants.USCID);
				String uuid = (String) studentJSON.get(DataConstants.UUID);
				String gradeYear = (String) studentJSON.get(DataConstants.GRADEYEAR);
				String advisor = (String) studentJSON.get(DataConstants.ADVISOR);
				String major = (String) studentJSON.get(DataConstants.MAJOR);
				String overallGrade = (String) studentJSON.get(DataConstants.OVERALLGRADE);
				String credits = (String) studentJSON.get(DataConstants.CREDITS);
				String completedClasses = (String) studentJSON.get(DataConstants.COMPLETEDCLASSES);
				String currentClasses = (String) studentJSON.get(DataConstants.GRADEYEAR);

				students.add(new Student(username, password, email, uscid));
			}

			return students;

=======
		
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
				Year gradeYear = (Year)studentJSON.get(GRADEYEAR);
				Advisor advisor = (Advisor)studentJSON.get(ADVISOR);
				Major major = (Major)studentJSON.get(MAJOR);
				double overallGrade = (double)studentJSON.get(OVERALLGRADE);
				int credits = (int)studentJSON.get(CREDITS);
				ArrayList<Course> completedCourses = (ArrayList<Course>)studentJSON.get(COMPLETEDCOURSES);
				ArrayList<Course> currentCourses = (ArrayList<Course>)studentJSON.get(CURRENTCOURSES);
				ArrayList<String> notes = (ArrayList<String>)studentJSON.get(NOTES);

				students.add( new Student(username, password, email, uscid, gradeYear,
				advisor, major, overallGrade, credits,
				completedCourses, currentCourses, notes));
			}
			
			return students;
			
>>>>>>> 161964660c18f96dd07cc7f551cc9c42056204f8
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}