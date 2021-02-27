package da;

import domain.Programme;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ProgrammeDA {

    private String host = "jdbc:derby://localhost:1527/collegedb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Programme";
    private Connection conn;
    private PreparedStatement stmt;

    public ProgrammeDA() {
    }

    public Programme getRecord(String code) throws SQLException {
        createConnection(); //CREATE CONNECTION WHEN WE NEED TO USE DATABASE (best practice)
        String queryStr = "SELECT * FROM " + tableName + " WHERE Code = ?";
        Programme programme = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                programme = new Programme(code, rs.getString("Name"), rs.getString("Faculty"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            shutDown();
        }
        return programme;
    }

    public void updateRecord(Programme programme) throws SQLException {
        createConnection();
        String queryStr = "UPDATE " + tableName + " SET Name=?, faculty=? where code=?";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, programme.getName());
            stmt.setString(2, programme.getFaculty());
            stmt.setString(3, programme.getCode());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            shutDown();
        }
    }

    public void addRecord(Programme programme) throws SQLException {
        createConnection();
        String queryStr = "INSERT INTO " + tableName + " VALUES(?,?,?)";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, programme.getCode());
            stmt.setString(2, programme.getName());
            stmt.setString(3, programme.getFaculty());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            shutDown();
        }
    }

    public void deleteRecord(String code) throws SQLException {
        createConnection();
        String queryStr = "DELETE FROM " + tableName + " where code = ?";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, code);
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
        ProgrammeDA da = new ProgrammeDA();
//        Programme programme = da.getRecord("IA");
//        System.out.println(programme);
    }
}
