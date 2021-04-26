
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class InventorySystem extends JFrame {

    private String host = "jdbc:derby://localhost:1527/ShopDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "PRODUCT";
    private Connection conn;
    private PreparedStatement stmt;

    private JTextField jtfProdID = new JTextField();
    private JTextField jtfDes = new JTextField();
    private JTextField jtfPrice = new JTextField();
    private JTextField jtfQtyOnHand = new JTextField();

    private JButton jbtnSearch = new JButton("Search");
    private JButton jbtnAdd = new JButton("Add New Product");
    private JButton jbtnStockOut = new JButton("Stock Out");
    private JButton jbtnStockIn = new JButton("Stock In");

    JFrame showTableFrame = new JFrame();

    public InventorySystem() {

        JPanel jpNorth = new JPanel(new GridLayout(4, 2));
        jpNorth.add(new JLabel("Product ID"));
        jpNorth.add(jtfProdID);
        jpNorth.add(new JLabel("Description"));
        jpNorth.add(jtfDes);
        jpNorth.add(new JLabel("Price"));
        jpNorth.add(jtfPrice);
        jpNorth.add(new JLabel("Quantity On Hand"));
        jpNorth.add(jtfQtyOnHand);
        add(jpNorth, BorderLayout.NORTH);

        JPanel jpCenter = new JPanel();
        jpCenter.add(jbtnSearch);
        jpCenter.add(jbtnAdd);
        jpCenter.add(jbtnStockOut);
        jpCenter.add(jbtnStockIn);
        add(jpCenter, BorderLayout.CENTER);

        jbtnSearch.addActionListener(new SearchListener());
        jbtnAdd.addActionListener(new CreateListener());
        jbtnStockIn.addActionListener(new StockInListenerClass());
        jbtnStockOut.addActionListener(new StockOutListenerClass());

        JMenuBar jmb = new JMenuBar();
        JMenu jmlShowRecords = new JMenu("Show Records");
        jmlShowRecords.setMnemonic('S');
        JMenuItem jmiRecords = new JMenuItem("Product Records");
        jmiRecords.setMnemonic(KeyEvent.VK_P);
        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK);
        jmiRecords.setAccelerator(ks);
        jmlShowRecords.add(jmiRecords);
        jmb.add(jmlShowRecords);

        JMenu jmlExit = new JMenu("Exit");
        jmlExit.setMnemonic('E');
        JMenuItem jmiClose = new JMenuItem("Close");
        jmiClose.setMnemonic('C');
        jmlExit.add(jmiClose);
        jmb.add(jmlExit);
        this.setJMenuBar(jmb);
        jmiRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DisplayProducts displayProducts = new DisplayProducts();
                    showTableFrame.add(displayProducts);
                    showTableFrame.setTitle("Product Records");
                    showTableFrame.setSize(500, 200);
                    showTableFrame.setVisible(true);
                    showTableFrame.setLocationRelativeTo(null);
                    showTableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jmiClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );

        setTitle("Inventory System");
        setSize(480, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public ResultSet selectRecord(String id) {
        createConnection();
        String queryStr = "SELECT * FROM " + tableName + " WHERE prodID = ?";
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

    public void addRecord(String id, String name, double price, int quantity) {
        createConnection();
        String queryStr = "INSERT INTO " + tableName + " VALUES(?,?,?,?)";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRecord(String id, int quantity) {
        createConnection();
        String queryStr = "UPDATE " + tableName + " SET quantityonhand=? where prodID=?";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setInt(1, quantity);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = jtfProdID.getText();
            String des = jtfDes.getText();
            String price = jtfPrice.getText();
            String qtyOnHand = jtfQtyOnHand.getText();

            ResultSet rs = selectRecord(id);
            try {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record Existing.");
                } else {
                    addRecord(id, des, Double.parseDouble(price), Integer.parseInt(qtyOnHand));
                    JOptionPane.showMessageDialog(null, "New Product added.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id = jtfProdID.getText();
                ResultSet rs = selectRecord(id);
                if (rs.next()) {
                    jtfDes.setText(rs.getString("Description"));
                    jtfPrice.setText(rs.getString("price"));
                    jtfQtyOnHand.setText(rs.getString("quantityonhand"));
                } else {
                    JOptionPane.showMessageDialog(null, "Product does not exits", "RECORD NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class StockInListenerClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = jtfProdID.getText();;

            try {
                ResultSet rs = selectRecord(id);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record not Existing.");
                } else {
                    int qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Stock In Quantuty:"));
                    if (qty > 0) {
                        int totalQty = qty + rs.getInt("quantityonhand");
                        updateRecord(id, totalQty);

                        JOptionPane.showMessageDialog(null, "Quantity updated " + totalQty);

                    } else {
                        JOptionPane.showMessageDialog(null, "Quantity must be greater than zero!!!");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class StockOutListenerClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = jtfProdID.getText();;

            try {
                ResultSet rs = selectRecord(id);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record not Existing.");
                } else {
                    int qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Stock Out Quantuty:"));
                    if (qty > 0) {
                        if (qty < rs.getInt("quantityonhand")) {
                            int totalQty = rs.getInt("quantityonhand") - qty;
                            updateRecord(id, totalQty);

                            JOptionPane.showMessageDialog(null, "Quantity updated " + totalQty);
                        } else {
                            JOptionPane.showMessageDialog(null, "Not enough stock!!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Quantity must be greater than zero!!!");
                    }
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
        new InventorySystem();
    }
}
