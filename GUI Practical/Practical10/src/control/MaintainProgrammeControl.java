package control;

import da.ProgrammeDA;
import domain.Programme;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaintainProgrammeControl {

    private ProgrammeDA progDA;

    public MaintainProgrammeControl() {
        progDA = new ProgrammeDA();
    }

    public Programme selectRecord(String code) throws SQLException {
        return progDA.getRecord(code);
    }

    public ArrayList<Programme> getAll() throws SQLException {
        return progDA.getProgrammes();
    }

}
