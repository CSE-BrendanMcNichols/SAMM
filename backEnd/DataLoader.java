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

	public static ArrayList<Student> loadStudents() {
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
//Made no changes