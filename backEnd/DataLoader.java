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

public class DataLoader extends DataConstants{
	
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
				String gradeYear = (String)studentJSON.get(GRADEYEAR);
				String advisor = (String)studentJSON.get(ADVISOR);
				String major = (String)studentJSON.get(MAJOR);
				String overallGrade = (String)studentJSON.get(OVERALLGRADE);
				String credits = (String)studentJSON.get(CREDITS);
				String completedClasses = (String)studentJSON.get(COMPLETEDCLASSES);
				String currentClasses = (String)studentJSON.get(GRADEYEAR);

				students.add(new Student(username, password, email, uscid, uuid));
			}
			
			return people;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}