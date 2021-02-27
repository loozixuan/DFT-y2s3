package da;

import control.MaintainProgrammeControl;
import domain.Student;
import domain.Programme;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class StudentDA {

    private String host = "jdbc:derby://localhost:1527/collegedb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Student";
    private Connection conn;
    private PreparedStatement stmt;

    public StudentDA() {

    }

    public Student getRecord(String id) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE id=?";
        createConnection();
        Student student = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student(id, rs.getString("ic"), rs.getString("name"),
                        rs.getString("level").charAt(0), new Programme(rs.getString("programmecode")), rs.getInt("yr"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            shutDown();
        }
        return student;
    }

    public void addRecord(Student student) throws SQLException {
        createConnection();
        String queryStr = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getIc());
            stmt.setString(3, student.getName());
            stmt.setString(4, String.valueOf(student.getLevel()));
            stmt.setString(5, student.getProgramme().getCode());
            stmt.setInt(6, student.getYear());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            shutDown();
        }
    }

    public void updateRecord(Student student) throws SQLException {
        createConnection();
        String queryStr = "UPDATE " + tableName + " SET ic=?,name=?,level=?,programmecode=?,yr=? where id=?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, student.getIc());
            stmt.setString(2, student.getName());
            stmt.setString(3, String.valueOf(student.getLevel()));
            stmt.setString(4, student.getProgramme().getCode());
            stmt.setInt(5, student.getYear());
            stmt.setString(6, student.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            shutDown();
        }
    }

    public void deleteRecord(String id) throws SQLException {
        createConnection();
        String queryStr = "DELETE FROM " + tableName + " where id = ?";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            shutDown();
        }
    }

    private void createConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private void shutDown() throws SQLException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw ex;
            }
        }
    }

    public static void main(String[] args) {
        StudentDA da = new StudentDA();

    }
}
