package backEnd;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will write or save the data
 */

public class DataWriter {
    /**
     * This method will save students list in a json file
     * 
     * @param studentList
     */
    public void saveStudents(ArrayList<Student> studentList) {
        // Convert ArrayList to JSON
        JSONArray jsonArray = new JSONArray();
        for (Student student : studentList) {
            JSONObject studentJSON = new JSONObject();
            studentJSON.put(DataConstants.USERNAME, student.getUsername());
            studentJSON.put(DataConstants.PASSWORD, student.getPassword());
            studentJSON.put(DataConstants.EMAIL, student.getEmail());
            studentJSON.put(DataConstants.USCID, student.getUscid());
            studentJSON.put(DataConstants.UUID, student.getUuid());
            studentJSON.put(DataConstants.GRADEYEAR, student.getGradeYear());
            studentJSON.put(DataConstants.ADVISOR, student.getAdvisor());
            studentJSON.put(DataConstants.MAJOR, student.getMajor());
            studentJSON.put(DataConstants.OVERALLGRADE, student.getOverallGrade());
            studentJSON.put(DataConstants.CREDITS, student.getCredits());
            studentJSON.put(DataConstants.COMPLETEDCOURSES, student.getcompletedCourses());
            studentJSON.put(DataConstants.CURRENTCOURSES, student.getCurrentCourses());

            jsonArray.add(studentJSON);
        }

        // Write JSON to file
        try (FileWriter file = new FileWriter(DataConstants.STUDENT_FILE_NAME)) {
            file.write(jsonArray.toJSONString());
            file.flush();
            System.out.println("Studnets JSON data is written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will save advisors list in a json file
     * 
     * @param advisorList
     */
    public void saveAdvisors(ArrayList<Advisor> advisorList) {
        // Convert ArrayList to JSON
        JSONArray jsonArray = new JSONArray();
        for (Advisor advisor : advisorList) {
            JSONObject advisorJSON = new JSONObject();
            advisorJSON.put(DataConstants.ASSIGNED_STUDENTS, advisor.getAssignedStudents());
            jsonArray.add(advisorJSON);
        }

        // Write JSON to file
        try (FileWriter file = new FileWriter(DataConstants.ADVISOR_FILE_NAME)) {
            file.write(jsonArray.toJSONString());
            file.flush();
            System.out.println("Advisors JSON data is written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will save administrators list in a json file
     * 
     * @param administratorList
     */
    public void saveAdministrators(ArrayList<Administrator> administratorList) {
        // Convert ArrayList to JSON
        JSONArray jsonArray = new JSONArray();
        for (Administrator administrator : administratorList) {
            JSONObject administratorJSON = new JSONObject();
            administratorJSON.put(DataConstants.USERNAME, administrator.getUsername());
            administratorJSON.put(DataConstants.PASSWORD, administrator.getPassword());
            administratorJSON.put(DataConstants.EMAIL, administrator.getEmail());
            administratorJSON.put(DataConstants.USCID, administrator.getUscid());
            administratorJSON.put(DataConstants.UUID, administrator.getUuid());

            jsonArray.add(administratorJSON);
        }

        // Write JSON to file
        try (FileWriter file = new FileWriter(DataConstants.ADMINISTRATOR_FILE_NAME)) {
            file.write(jsonArray.toJSONString());
            file.flush();
            System.out.println("Administrator JSON data is written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveMajors(ArrayList<Major> majorList) {
       // Convert ArrayList to JSON
       JSONArray jsonArray = new JSONArray();
       for (Major major : majorList) {
           JSONObject majorJSON = new JSONObject();
           majorJSON.put(DataConstants.MAJOR, major.getMajor());
           majorJSON.put(DataConstants.COREREQ, major.getCoreReq());
           majorJSON.put(DataConstants.COURSES, major.getCourses());
           majorJSON.put(DataConstants.ELECTIVE_COURSES, major.getElectiveCourses());
           majorJSON.put(DataConstants.MAJOR, major.getMajor());
          
           jsonArray.add(majorJSON);

       }

       // Write JSON to file
       try (FileWriter file = new FileWriter(DataConstants.ADVISOR_FILE_NAME)) {
           file.write(jsonArray.toJSONString());
           file.flush();
           System.out.println("Majors JSON data is written to the file.");
       } catch (IOException e) {
           e.printStackTrace();
       };
    }

}
