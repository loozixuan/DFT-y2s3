package control;

import da.StudentDA;
import domain.Student;

public class MaintainStudentControl {

    private StudentDA studDA;

    public MaintainStudentControl() {
        studDA = new StudentDA();
    }

    public Student selectRecord(String id) {
        return studDA.getRecord(id);
    }

    public void addRecord(Student p) {
        studDA.addRecord(p);
    }

    public void updateRecord(Student p) {
        studDA.updateRecord(p);
    }

    public void deleteRecord(String id) {
        studDA.deleteRecord(id);
    }

}
