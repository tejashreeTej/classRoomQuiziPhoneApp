import java.sql.SQLException;
import java.util.ArrayList;

import edu.npu.library.database.StudentDatabase;
import edu.npu.library.domain.Student;

public class ClientApplication {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Student> studentList= StudentDatabase.getStudentList();
		for(int i=0; i<studentList.size(); i++){
			Student stud=studentList.get(i);
			System.out.println("StudentId : "+stud.getStudentId()+" StudentName : "+stud.getStudentName());
		}
	}

}

