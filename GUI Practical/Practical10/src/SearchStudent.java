
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SearchStudent extends JFrame {
    
    private StudentTableModel tableModel;
    private JTextField jtfCode;
    private JTable studentTable;
    private TableRowSorter<TableModel> sorter;
    
    public SearchStudent() {
        
        tableModel = new StudentTableModel();
        jtfCode = new JTextField();
        studentTable = new JTable();
        add(jtfCode, BorderLayout.NORTH);
        studentTable = new JTable(tableModel);
        jtfCode.addActionListener(new SearchListener());

        //JMenubar 
        JMenuBar jmb = new JMenuBar();
        JMenu jml = new JMenu("System");
        jml.setMnemonic(KeyEvent.VK_S);
        JMenuItem jmi = new JMenuItem("Exit");
        jmi.setMnemonic(KeyEvent.VK_E);
        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK);
        jmi.setAccelerator(ks);
        jml.add(jmi);
        jmb.add(jml);
        
        jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );
        
        this.setJMenuBar(jmb);
        add(new JScrollPane(studentTable));
        sorter = new TableRowSorter<TableModel>(tableModel);
        studentTable.setRowSorter(sorter);
        
        setTitle("Student Information");
        setSize(600, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private class SearchListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            tableModel.retrieveRecordsByProgramme(code);
        }
    }
    
    public static void main(String[] args) {
        new SearchStudent();
    }
}
