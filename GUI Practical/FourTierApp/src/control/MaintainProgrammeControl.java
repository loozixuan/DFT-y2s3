package control;

import da.ProgrammeDA;
import domain.Programme;
import java.sql.SQLException;

public class MaintainProgrammeControl {

    private ProgrammeDA progDA;

    public MaintainProgrammeControl() {
        progDA = new ProgrammeDA();
    }

    public Programme selectRecord(String code) throws SQLException {
        return progDA.getRecord(code);
    }

    public void updateRecord(Programme programme) throws SQLException {
        progDA.updateRecord(programme);
    }

    public void addRecord(Programme programme) throws SQLException {
        progDA.addRecord(programme);
    }

    public void deleteRecord(String code) throws SQLException {
        progDA.deleteRecord(code);
    }

}
