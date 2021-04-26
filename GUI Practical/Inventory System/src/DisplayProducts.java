
import java.sql.SQLException;
import javax.swing.*;

public class DisplayProducts extends JPanel {

    private String host = "jdbc:derby://localhost:1527/ShopDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String query = "SELECT * FROM PRODUCT";
    private ResultSetTableModel tableModel;
    private JTable productTable;

    public DisplayProducts() throws SQLException {
        tableModel = new ResultSetTableModel(host, user, password, query);
        productTable = new JTable(tableModel);
        add(new JScrollPane(productTable));
    }

}
