package control;

import domain.Student;
import da.StudentDA;
import java.sql.SQLException;

public class MaintainStudentControl {

    private StudentDA studDA;

    public MaintainStudentControl() {
        studDA = new StudentDA();
    }

    public Student selectRecord(String id) throws SQLException {
        return studDA.getRecord(id);
    }

    public void updateRecord(Student student) throws SQLException {
        studDA.updateRecord(student);
    }

    public void addRecord(Student student) throws SQLException {
        studDA.addRecord(student);
    }

    public void deleteRecord(String code) throws SQLException {
        studDA.deleteRecord(code);
    }

}
