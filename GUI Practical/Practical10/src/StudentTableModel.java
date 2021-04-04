// Coverred in Practical Test

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

public class StudentTableModel extends AbstractTableModel {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String host = "jdbc:derby://localhost:1527/collegedb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String studTable = "Student";

    private int numberOfRows;

    public StudentTableModel() {
        createConnection();
        retrieveAll();
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rs.getMetaData().getColumnName(column + 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public int getRowCount() {
        return this.numberOfRows;
    }

    @Override
    public int getColumnCount() {
        try {
            return rs.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex + 1);
            return rs.getObject(columnIndex + 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return "";
    }

    public void retrieveAll() {
        String query = "SELECT * FROM " + studTable;
        try {
            rs = stmt.executeQuery(query);
            setResultSetVariables();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    private void setResultSetVariables() {
        try {
            // find out how many rows in table
            rs.last();
            numberOfRows = rs.getRow();
            fireTableStructureChanged();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            //TYPE_SCROLL_INSENSITIVE
            //This type of ResultSet is insensitive to the changes that are made in the database i.e.
            //the modifications done in the database are not reflected in the ResultSet.

            //CONCUR_READ_ONLY
            //This type of result set is not updatable.
            //i.e.once you get a ResultSet object you cannot update its contents.
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    public void retrieveRecordsByProgramme(String progCode) {
        try {
            String query = "SELECT * FROM " + studTable + " WHERE ProgrammeCode = '" + progCode + "'";
            rs = stmt.executeQuery(query);
            setResultSetVariables();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
}
