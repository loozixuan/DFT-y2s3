package ui;

import control.MaintainProgrammeControl;
import domain.Programme;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class DisplayProgramme extends JFrame {

    private MaintainProgrammeControl progControl;
    private JComboBox jcboCode;
    private JTextField jtfName;
    private JTextField jtfFaculty;

    public DisplayProgramme() {
        progControl = new MaintainProgrammeControl();

        jcboCode = new JComboBox();
        jtfName = new JTextField();
        jtfFaculty = new JTextField();
        initializeModel();

        add(jcboCode, BorderLayout.NORTH);
        jcboCode.addActionListener(new SelectListener());

        JPanel jpCenter = new JPanel(new GridLayout(2, 2));
        jpCenter.add(new JLabel("Programme name"));
        jpCenter.add(jtfName);
        jpCenter.add(new JLabel("Faculty"));
        jpCenter.add(jtfFaculty);
        jtfName.setEditable(false);
        jtfFaculty.setEditable(false);
        add(jpCenter);

        setTitle("Search Programme");
        setSize(500, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeModel() {
        try {
            ArrayList<Programme> programmes = new ArrayList<Programme>();
            programmes = progControl.getAll();
            DefaultComboBoxModel dcbm = new DefaultComboBoxModel();

            for (int i = 0; i < programmes.size(); i++) {
                dcbm.addElement(programmes.get(i).getCode());
            }
            jcboCode = new JComboBox(dcbm);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setTextFields(Programme p) {
        jtfName.setText(p.getName());
        jtfFaculty.setText(p.getFaculty());
    }

    private class SelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String code = (String) jcboCode.getSelectedItem();
                Programme programme = progControl.selectRecord(code);
                setTextFields(programme);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public static void main(String[] args) {
        DisplayProgramme displayProgramme = new DisplayProgramme();
    }
}
