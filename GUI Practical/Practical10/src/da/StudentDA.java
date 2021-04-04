package da;

import domain.Programme;
import domain.Student;
import java.sql.*;
import javax.swing.*;

public class StudentDA {

    private final static String host = "jdbc:derby://localhost:1527/collegedb";
    private final static String user = "nbuser";
    private final static String password = "nbuser";
    private final static String tableName = "Student";
    private Connection conn;
    private PreparedStatement stmt;
    private ProgrammeDA progDA;

    public StudentDA() {
        progDA = new ProgrammeDA();
    }

    public void addRecord(Student student) {
        createConnection();
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getIc());
            stmt.setString(3, student.getName());
            stmt.setString(4, String.valueOf(student.getLevel()));
            stmt.setString(5, student.getProgramme().getCode());
            stmt.setInt(6, student.getYear());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            shutDown();
        }
    }

    public Student getRecord(String id) {
        createConnection();
        String queryStr = "SELECT * FROM " + tableName + " WHERE ID = ?";
        Student student = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Programme programme = progDA.getRecord(rs.getString("ProgrammeCode"));
                student = new Student(id, rs.getString("IC"),
                        rs.getString("Name"), rs.getString("Level").charAt(0),
                        programme, rs.getInt("Yr"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            shutDown();
        }
        return student;
    }

    public void updateRecord(Student student) {
        createConnection();
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET IC = ?, Name = ?, Level = ?, ProgrammeCode = ?, Yr = ? WHERE ID = ?";
            System.out.println("\n***TRACE Line 63: sql string: " + updateStr);
            stmt = conn.prepareStatement(updateStr);
            System.out.println("\n***TRACE Line 65: sql string: " + updateStr);
            stmt.setString(1, student.getIc());
            stmt.setString(2, student.getName());
            stmt.setString(3, String.valueOf(student.getLevel()));
            stmt.setString(4, student.getProgramme().getCode());
            System.out.println("\n***TRACE: sql string: " + updateStr);
            stmt.setInt(5, student.getYear());
            System.out.println("\n***TRACE: sql string: " + updateStr);
            stmt.setString(6, student.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("******ERROR: " + ex.getMessage());
        } finally {
            shutDown();
        }
    }

    public void deleteRecord(String id) {
        createConnection();
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE ID = ?";
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            shutDown();
        }
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            shutDown();
        }
    }

    private void shutDown() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
