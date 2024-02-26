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

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}