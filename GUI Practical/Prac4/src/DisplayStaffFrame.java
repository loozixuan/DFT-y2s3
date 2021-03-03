
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class DisplayStaffFrame extends JFrame {

    private String host = "jdbc:derby://localhost:1527/staff";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "staff";
    private Connection conn;
    private PreparedStatement stmt;

    private JTextField jtfID = new JTextField();
    private JTextField jtfLastName = new JTextField();
    private JTextField jtfFirstName = new JTextField();
    private JTextField jtfMi = new JTextField();
    private JTextField jtfAddress = new JTextField();
    private JTextField jtfCity = new JTextField();
    private JTextField jtfState = new JTextField();
    private JTextField jtfTelephone = new JTextField();
    private JTextField jtfEmail = new JTextField();

    private JButton jbtView = new JButton("View");
    private JButton jbtInsert = new JButton("Insert");
    private JButton jbtReset = new JButton("Reset");

    public DisplayStaffFrame() {
        JPanel jpCenter = new JPanel(new GridLayout(9, 2));
        jpCenter.add(new JLabel("ID"));
        jpCenter.add(jtfID);
        jpCenter.add(new JLabel("Last Name"));
        jpCenter.add(jtfLastName);
        jpCenter.add(new JLabel("First Name"));
        jpCenter.add(jtfFirstName);
        jpCenter.add(new JLabel("mi"));
        jpCenter.add(jtfMi);
        jpCenter.add(new JLabel("Address"));
        jpCenter.add(jtfAddress);
        jpCenter.add(new JLabel("City"));
        jpCenter.add(jtfCity);
        jpCenter.add(new JLabel("State"));
        jpCenter.add(jtfState);
        jpCenter.add(new JLabel("Telephone"));
        jpCenter.add(jtfTelephone);
        jpCenter.add(new JLabel("E-mail"));
        jpCenter.add(jtfEmail);
        jpCenter.setBorder(new TitledBorder("Staff Information"));

        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtView);
        jpSouth.add(jbtInsert);
        jpSouth.add(jbtReset);

        jbtView.addActionListener(new ViewListener());
        jbtInsert.addActionListener(new InsertListener());
        jbtReset.addActionListener(new ResetListener());

        add(jpCenter, BorderLayout.CENTER);
        add(jpSouth, BorderLayout.SOUTH);

        setTitle("Register Staff");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public ResultSet selectRecord(String id) {
        createConnection();
        String queryStr = "SELECT * FROM " + tableName + " WHERE id = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    public void addRecord(String id, String lastName, String firstName, char mi, String address, String city, String state, String phone, String email) {
        createConnection();
        String queryStr = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            stmt.setString(2, lastName);
            stmt.setString(3, firstName);
            stmt.setString(4, String.valueOf(mi));
            stmt.setString(5, address);
            stmt.setString(6, city);
            stmt.setString(7, state);
            stmt.setString(8, phone);
            stmt.setString(9, email);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = jtfID.getText();
            String lastName = jtfLastName.getText();
            String firstName = jtfFirstName.getText();
            String mi = jtfMi.getText();
            String address = jtfAddress.getText();
            String city = jtfCity.getText();
            String state = jtfState.getText();
            String telephone = jtfTelephone.getText();
            String email = jtfEmail.getText();

            ResultSet rs = selectRecord(id);
            try {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record Existing.");
                } else {
                    addRecord(id, lastName, firstName, mi.charAt(0), address, city, state, telephone, email);
                    JOptionPane.showMessageDialog(null, "New Staff added.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jtfID.setText("");
            jtfLastName.setText("");
            jtfFirstName.setText("");
            jtfMi.setText("");
            jtfAddress.setText("");
            jtfCity.setText("");
            jtfState.setText("");
            jtfTelephone.setText("");
            jtfEmail.setText("");
        }

    }

    class ViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id = jtfID.getText();
                ResultSet rs = selectRecord(id);
                if (rs.next()) {
                    jtfLastName.setText(rs.getString("lastname"));
                    jtfFirstName.setText(rs.getString("firstname"));
                    jtfMi.setText(rs.getString("mi"));
                    jtfAddress.setText(rs.getString("address"));
                    jtfCity.setText(rs.getString("city"));
                    jtfState.setText(rs.getString("state"));
                    jtfTelephone.setText(rs.getString("telephone"));
                    jtfEmail.setText(rs.getString("email"));
                } else {
                    JOptionPane.showMessageDialog(null, "No such ID.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
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
        DisplayStaffFrame frm = new DisplayStaffFrame();
    }
}
