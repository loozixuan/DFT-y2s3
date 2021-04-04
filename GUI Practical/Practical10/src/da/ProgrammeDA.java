package da;

import domain.Programme;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ProgrammeDA {

    private final static String host = "jdbc:derby://localhost:1527/collegedb";
    private final static String user = "nbuser";
    private final static String password = "nbuser";
    private final static String tableName = "Programme";
    private Connection conn;
    private PreparedStatement stmt;

    public ArrayList<Programme> getProgrammes() throws SQLException {
        createConnection();
        ArrayList<Programme> programmes = new ArrayList<Programme>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + tableName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                programmes.add(new Programme(rs.getString("Code"), rs.getString("Name"), rs.getString("Faculty")));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return programmes;
    }

    public Programme getRecord(String code) throws SQLException {
        createConnection();
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

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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

    public static void main(String[] args) {
        try {
            ProgrammeDA programmeDA = new ProgrammeDA();
            ArrayList<Programme> programmes = new ArrayList<Programme>();
            programmes = programmeDA.getProgrammes();
            JOptionPane.showMessageDialog(null, programmes.get(0).getCode());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
