
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class SimpleCRUD extends JFrame {

    private String host = "jdbc:derby://localhost:1527/collegedb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Programme";
    private Connection conn;
    private PreparedStatement stmt;
    private JTextField jtfCode = new JTextField();
    private JTextField jtfName = new JTextField();
    private JTextField jtfFaculty = new JTextField();
    private JButton jbtAdd = new JButton("Create");
    private JButton jbtRetrieve = new JButton("Retrieve");
    private JButton jbtUpdate = new JButton("Update");
    private JButton jbtDelete = new JButton("Delete");

    public SimpleCRUD() {
        JPanel jpCenter = new JPanel(new GridLayout(3, 2));
        jpCenter.add(new JLabel("Programme Code"));
        jpCenter.add(jtfCode);
        jpCenter.add(new JLabel("Programme Name"));
        jpCenter.add(jtfName);
        jpCenter.add(new JLabel("Faculty"));
        jpCenter.add(jtfFaculty);
        add(jpCenter);

        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtAdd);
        jpSouth.add(jbtRetrieve);
        jpSouth.add(jbtUpdate);
        jpSouth.add(jbtDelete);
        add(jpSouth, BorderLayout.SOUTH);

        jbtAdd.addActionListener(new AddListener());
        jbtRetrieve.addActionListener(new RetrieveListener());
        jbtUpdate.addActionListener(new UpdateListener());
        jbtDelete.addActionListener(new DeleteListener());

        setTitle("Programme CRUD");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public ResultSet selectRecord(String code) {
        createConnection();
        String queryStr = "SELECT * FROM " + tableName + " WHERE Code = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, code);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    public void addRecord(String code, String name, String faculty) {
        createConnection();
        String queryStr = "INSERT INTO " + tableName + " VALUES(?,?,?)";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, code);
            stmt.setString(2, name);
            stmt.setString(3, faculty);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRecord(String code, String name, String faculty) {
        createConnection();
        String queryStr = "UPDATE " + tableName + " SET Name=?, faculty=? where code=?";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, code);
            stmt.setString(2, name);
            stmt.setString(3, faculty);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteRecord(String code) {
        createConnection();
        String queryStr = "DELETE FROM " + tableName + " where code = ?";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearText() {
        jtfCode.setText("");
        jtfName.setText("");
        jtfFaculty.setText("");
    }

    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = jtfName.getText();
            String faculty = jtfFaculty.getText();
            String code = jtfCode.getText();
            ResultSet rs = selectRecord(code);
            try {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record Existing.");
                } else {
                    addRecord(code, name, faculty);
                    JOptionPane.showMessageDialog(null, "New Programme added.");
                    clearText();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String code = jtfCode.getText();
                ResultSet rs = selectRecord(code);
                if (rs.next()) {
                    jtfName.setText(rs.getString("Name"));
                    jtfFaculty.setText(rs.getString("Faculty"));
                } else {
                    JOptionPane.showMessageDialog(null, "No such programme code.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = jtfName.getText();
            String faculty = jtfFaculty.getText();
            String code = jtfCode.getText();

            try {
                ResultSet rs = selectRecord(code);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record not Existing.");
                } else {
                    updateRecord(name, faculty, code);
                    JOptionPane.showMessageDialog(null, "Record Updated.");
                }
                clearText();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String code = jtfCode.getText();
                ResultSet rs = selectRecord(code);
                if (rs.next()) {
                    jtfName.setText(rs.getString("Name"));
                    jtfFaculty.setText(rs.getString("Faculty"));
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure? ");
                    if (option == JOptionPane.YES_OPTION) {
                        deleteRecord(code);
                        JOptionPane.showMessageDialog(null, "Record deleted.");
                        clearText();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No such programme code.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SimpleCRUD frm = new SimpleCRUD();
    }
}
